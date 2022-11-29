using Grpc.Core;
using Grpc.Net.Client;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Channels;
using System.Threading.Tasks;
using GrpcData.DI;
using Microsoft.Extensions.Configuration;

namespace GrpcData
{
    internal class GrpcService : IGrpcService
    {
        private readonly string _url;
        private readonly IConfiguration _config;

        public GrpcService(IConfiguration _config)
        {
            this._config = _config;
            this._url = this._config.GetValue<string>("GrpcUrl")!;
        }

        public sepService.sepServiceClient CreateUserServiceClient()
        {
            var channel = GrpcChannel.ForAddress(_url);
            return new sepService.sepServiceClient(channel);
        }
        public postService.postServiceClient CreatePostServiceClient()
        {
            var channel = GrpcChannel.ForAddress(_url);
            return new postService.postServiceClient(channel);
        }
    }
}
