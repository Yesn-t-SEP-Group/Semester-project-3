using Application.DaoInterfaces;
using Application.Logic;
using Application.LogicInterfaces;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Application.DI
{
    public static class ServiceCollectionExtension
    {
        public static IServiceCollection AddBusinessLogic(this IServiceCollection services)
        {
            services.AddScoped<IUserLogic, UserLogic>();
            services.AddScoped<IPostLogic, PostLogic>();
            return services;
        }
    }
}
