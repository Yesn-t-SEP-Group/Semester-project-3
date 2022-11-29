using Application.DI;
using Domain.Auth;
using Domain.Models;
using FileData.DI;
using GrpcData.DI;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Diagnostics;
using Microsoft.IdentityModel.Tokens;
using Microsoft.OpenApi.Models;
using Serilog;
using System.Text;
using WebAPI.Services;

namespace WebAPI
{
    internal class Startup
    {
        [STAThread]
        public static void Main(string[] args)
        {
            IConfiguration config;

            try
            {
                config = new ConfigurationBuilder()
                    .SetBasePath(Directory.GetCurrentDirectory())
                    .AddJsonFile("appsettings.json", optional: false)
                    .Build();
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error occured while initializing: \n" + ex.Message + "\n" + ex.StackTrace);
                return;
            }

            var builder = WebApplication.CreateBuilder(args);

            Log.Logger = new LoggerConfiguration()
                .Enrich.WithProperty("app", builder.Environment.ApplicationName)
                .Enrich.FromLogContext()
                .WriteTo.File($"logs\\log_.txt", rollingInterval: RollingInterval.Day)
                .WriteTo.Console(outputTemplate: "[{Timestamp:u}] [{Level:u3}] {SourceContext} {Message:lj}{NewLine}{Exception}")
                .ReadFrom.Configuration(config)
                .CreateLogger();

            builder.Host.UseSerilog();

            Log.Logger.Information("Starting WebAPI");

            var services = builder.Services;
            services.AddSingleton<IConfiguration>(config);

            services.AddBusinessLogic();

            bool useGRPC = config.GetValue<bool>("UseGRPC");
            if (useGRPC)
            {
                services.AddGrpcDaos();
                Log.Logger.Information("Using GRPC DAOs");
            }
            else
            {
                services.AddFileDaos();
                Log.Logger.Information("Using file DAOs");
            }

            AuthorizationPolicies.AddPolicies(builder.Services);
            builder.Services.AddScoped<IAuthService, AuthService>();

            builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme).AddJwtBearer(options =>
            {
                options.RequireHttpsMetadata = false;
                options.SaveToken = true;
                options.TokenValidationParameters = new TokenValidationParameters()
                {
                    ValidateIssuer = true,
                    ValidateAudience = true,
                    ValidAudience = builder.Configuration["Jwt:Audience"],
                    ValidIssuer = builder.Configuration["Jwt:Issuer"],
                    IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(builder.Configuration["Jwt:Key"]))
                };
            });

            services.AddAutoMapper(typeof(MapperConfiguration));

            services.AddControllers();


            // Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
            builder.Services.AddEndpointsApiExplorer();
            builder.Services.AddSwaggerGen();

            var app = builder.Build();

            app.UseCors(x => x
                .AllowAnyMethod()
                .AllowAnyHeader()
                .SetIsOriginAllowed(origin => true) // allow any origin
                .AllowCredentials());

            if (app.Environment.IsDevelopment())
            {
                app.UseSerilogRequestLogging();
                app.UseSwagger();
                app.UseSwaggerUI();
            }

            app.UseExceptionHandler(error =>
            {
                error.Run(async context =>
                {
                    context.Response.StatusCode = StatusCodes.Status500InternalServerError;
                    context.Response.ContentType = "application/json";
                    var contextFeature = context.Features.Get<IExceptionHandlerFeature>();
                    if (contextFeature != null)
                    {
                        await context.Response.WriteAsync(new Error()
                        {
                            StatusCode = context.Response.StatusCode,
                            Message = "Unhandled error",
                        }.ToString());
                    }
                });
            });

            app.UseHttpsRedirection();

            app.UseAuthorization();

            app.MapControllers();

            app.UseAuthentication();

            app.Run();
        }
    }
}
