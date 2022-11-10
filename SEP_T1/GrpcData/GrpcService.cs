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
            this._url = this._config.GetValue<string>("GrpcUrl");
        }

        public sepService.sepServiceClient CreateServiceClient()
        {
            using var channel = GrpcChannel.ForAddress(_url);
            return new sepService.sepServiceClient(channel);
        }

        public GrpcService()
        {
            //GrpcData.Protos.UserDTO;

            //sepService.sepServiceClient
/*
            var client = new User.GreeterClient(channel);

            try
            {
                var response = await client.SayHelloAsync(
                    new HelloRequest { Name = "World" },
                    deadline: DateTime.UtcNow.AddSeconds(5));

                // Greeting: Hello World
                Console.WriteLine("Greeting: " + response.Message);
            }
            catch (RpcException ex) when (ex.StatusCode == StatusCode.DeadlineExceeded)
            {
                Console.WriteLine("Greeting timeout.");
            }*/
        }

        /*public async Task<User> createUser(UserCreationDto creationDto)
        {
            using var channel = GrpcChannel.ForAddress(_url);
            client = new sepService.sepServiceClient(channel);

            UserDTO dto = new UserDTO();
            dto.Address = creationDto.Address;
            dto.Username = creationDto.UserName;
            dto.UserPass = creationDto.Password;
            dto.Email = creationDto.Email;
            dto.FullName = creationDto.Name;
            dto.PhoneNumber = creationDto.PhoneNumber;
            var reply = await client.createUserAsync(dto);
            source.Cancel();
            return reply;
        }*/
    }
}
