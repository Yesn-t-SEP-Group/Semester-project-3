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
        public static IServiceCollection AddGrpcServices(this IServiceCollection services)
        {
            services.AddScoped<IGrpcService, GrpcService>();
            return services;
        }
    }
}
