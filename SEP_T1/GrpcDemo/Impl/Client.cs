using Grpc.Net.Client;

namespace GrpcDemo.Impl;

public class Client : BackgroundService
{
    private string _url = "http://localhost:6565";

    
    protected override async Task ExecuteAsync(CancellationToken stoppingToken)
    {
        using var channel = GrpcChannel.ForAddress(_url);
        var client = new sepService.sepServiceClient(channel);
        while (!stoppingToken.IsCancellationRequested)
        {
            //TODO not hardcode,get from POST
            UserDTO dto = new UserDTO{
                Address = "Horsens",
                Email = "test@test.dk",
                FullName = "TEST",
                PhoneNumber = "+4500000000",
                Username = "BingBong"
                };
            var reply = await client.createUserAsync(dto);
            await Task.Delay(1000, stoppingToken);
        }
    }
}