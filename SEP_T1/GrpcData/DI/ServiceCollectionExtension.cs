using Application.DaoInterfaces;
using GrpcData.DAOs;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GrpcData.DI
{
    public static class ServiceCollectionExtension
    {
        public static IServiceCollection AddGrpcDaos(this IServiceCollection services)
        {
            services.AddScoped<IGrpcService, GrpcService>();
            services.AddScoped<IUserDao, UserGrpcDao>();
            services.AddScoped<IPostDao, PostGrpcDao>();
            services.AddScoped<IReportDao, ReportGrpcDao>();
            services.AddScoped<IRatingDao, RatingGrpcDao>();
            return services;
        }
    }
}
