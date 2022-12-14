using Microsoft.AspNetCore.Components.Web;
using Microsoft.AspNetCore.Components.WebAssembly.Hosting;
using Blazored.Modal;
using BlazorWASM;
using BlazorWASM.Auth;
using Domain.Auth;
using HttpClients.ClientInterfaces;
using HttpClients.Implementations;
using Microsoft.AspNetCore.Components.Authorization;

var builder = WebAssemblyHostBuilder.CreateDefault(args);
builder.RootComponents.Add<App>("#app");
builder.RootComponents.Add<HeadOutlet>("head::after");

builder.Services.AddScoped(sp => new HttpClient { BaseAddress = new Uri(builder.HostEnvironment.BaseAddress) });
builder.Services.AddScoped(
    sp => 
        new HttpClient { 
            BaseAddress = new Uri("https://localhost:7130") 
        }
);
builder.Services.AddScoped<IUserService, UserHttpClient>();
builder.Services.AddScoped<IPostService, PostHttpClient>();
builder.Services.AddScoped<IAuthService, JwtAuthService>();
builder.Services.AddScoped<IReportService, ReportHttpClient>();
builder.Services.AddScoped<IMessageService, MessageHttpClient>();
builder.Services.AddScoped<IRatingService, RatingHttpClient>();
builder.Services.AddScoped<AuthenticationStateProvider, CustomAuthProvider>();


AuthorizationPolicies.AddPolicies(builder.Services);
builder.Services.AddBlazoredModal();

await builder.Build().RunAsync();