using Application.DaoInterfaces;
using Application.Logic;
using Application.LogicInterfaces;
using FileData.DAOs;
using Microsoft.Extensions.DependencyInjection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FileData.DI
{
    public static class ServiceCollectionExtension
    {
        public static IServiceCollection AddFileDaos(this IServiceCollection services)
        {
            services.AddScoped<FileContext>();
            services.AddScoped<IUserDao, UserFileDao>();
            services.AddScoped<IPostDao, PostFileDao>();
            return services;
        }
    }
}
