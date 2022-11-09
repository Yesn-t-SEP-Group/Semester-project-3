using Domain.DTOs;
using Grpc.Net.Client;

namespace GrpcDemo.Impl;

public class Client : BackgroundService
{
    private string _url = "http://localhost:6565";
    private sepService.sepServiceClient client;
    private static CancellationTokenSource source = new CancellationTokenSource();
    private CancellationToken _cancellationToken = source.Token;
    protected override async Task ExecuteAsync(CancellationToken stoppingToken)
    {
        _cancellationToken = stoppingToken;
        while (!_cancellationToken.IsCancellationRequested)
        {
            using var channel = GrpcChannel.ForAddress(_url);
            client = new sepService.sepServiceClient(channel);
        }
    }

    public async Task<User> createUser(UserCreationDto creationDto)
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
    }
}