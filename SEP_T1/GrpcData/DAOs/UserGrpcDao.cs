using Application.DaoInterfaces;
using AutoMapper;
using Domain.DTOs;
using Domain.Models;
using Grpc.Net.Client;
using GrpcData.DI;

namespace GrpcData.DAOs;

public class UserGrpcDao : IUserDao
{
    private readonly IGrpcService _grpcService;
    private readonly IMapper _mapper;

    public UserGrpcDao(
        IGrpcService grpcService,
        IMapper mapper)
    {
        this._grpcService = grpcService;
        _mapper = mapper;
    }

    public Task<UserReadDto?> GetByUsernameAsync(string userName)
    {
        //todo do we need this
        throw new NotImplementedException();
    }

    public async Task<UserReadDto> CreateAsync(UserCreationDto user)
    {
        var client = this._grpcService.CreateServiceClient();
        try
        {
            var convertedToGrpc = this._mapper.Map<UserCreationGrpcDto>(user);
            var result= await client.createUserAsync(convertedToGrpc);
            return this._mapper.Map<UserReadDto>(result);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            throw;
        }
    }

    public Task<IEnumerable<UserReadDto>> GetAllAsync()
    {
        throw new NotImplementedException();
    }

    public Task<UserReadDto?> GetByIdAsync(int id)
    {
        throw new NotImplementedException();
    }

    public Task DeleteAsync(int id)
    {
        throw new NotImplementedException();
    }

    public Task<UserReadDto?> LoginAsync(UserLoginDto dto)
    {
        throw new NotImplementedException();
    }


    /*public User ConvertToGrpc(Domain.Models.User modelUser)
    {
        User ret = new User
        {
            Address = modelUser.Address,
            Email = modelUser.Email,
            Id = modelUser.Id,
            Username = modelUser.UserName,
            UserPass = modelUser.Password,
            FullName = modelUser.Name,
            PhoneNumber = modelUser.PhoneNumber,
            RegisteredOn = (int)modelUser.registeredOn.ToTimestamp().Seconds,
            LastSeen = (int)modelUser.lastSeen.ToTimestamp().Seconds
        };
        return ret;
    }

    public Domain.Models.User ConvertFromGrpc(User grpcUser)
    {
        var epoch = DateTime.UnixEpoch;
        var ret = new Domain.Models.User()
        {
            Address = grpcUser.Address,
            Email = grpcUser.Email,
            Id = grpcUser.Id,
            registeredOn = epoch.AddSeconds(grpcUser.RegisteredOn),
            lastSeen = epoch.AddSeconds(grpcUser.LastSeen),
            Name = grpcUser.FullName,
            Password = grpcUser.UserPass,
            PhoneNumber = grpcUser.PhoneNumber
        };
        return ret;
    }
    /*
    private readonly FileContext context;

    public UserFileDao(FileContext context)
    {
        this.context = context;
    }

    public Task<User> CreateAsync(User user)
    {
        int userId = 1;
        if (context.Users.Any())
        {
            userId = context.Users.Max(u => u.Id);
            userId++;
        }

        user.Id = userId;

        context.Users.Add(user);
        context.SaveChanges();

        return Task.FromResult(user);
    }

    public Task<User?> GetByUsernameAsync(string userName)
    {
        User? existing = context.Users.FirstOrDefault(u =>
            u.UserName.Equals(userName, StringComparison.OrdinalIgnoreCase)
        );
        return Task.FromResult(existing);
    }

    public Task<IEnumerable<User>> GetAsync(SearchUserParametersDto searchParameters)
    {
        IEnumerable<User> users = context.Users.AsEnumerable();
        if (searchParameters.UsernameContains != null)
        {
            users = context.Users.Where(u => u.UserName.Contains(searchParameters.UsernameContains, StringComparison.OrdinalIgnoreCase));
        }

        return Task.FromResult(users);
    }

    public Task<User?> GetByIdAsync(int id)
    {
        User? existing = context.Users.FirstOrDefault(u =>
            u.Id == id
        );
        return Task.FromResult(existing);
    }

    */
    
}