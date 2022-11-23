using Application.DaoInterfaces;
using AutoMapper;
using Domain.DTOs;
using Domain.Models;
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
        var client = _grpcService.CreateServiceClient();

        var convertedToGrpc = this._mapper.Map<UserCreationGrpcDto>(user);
        
        var result= await client.createUserAsync(convertedToGrpc);
        return this._mapper.Map<UserReadDto>(result);
    }

    public async Task<IEnumerable<UserReadDto>> GetAllAsync()
    {
        var client = _grpcService.CreateServiceClient();

        ;
        var result = await client.getAllUsersAsync(new Empty());
        List<UserReadDto> list=new List<UserReadDto>();
        foreach (var user in result.Users)
        {
            var temp = _mapper.Map<UserReadDto>(user);
            list.Add(temp);
        }

        return list;
    }

    public async Task<UserReadDto?>GetByIdAsync(int id)
    {
        var client = _grpcService.CreateServiceClient();
        var result = await client.getUserByIdAsync(new GenericMessage{Message = id.ToString()});

        return this._mapper.Map<UserReadDto>(result);
    }

    public async Task DeleteAsync(int id)
    {
        var client = _grpcService.CreateServiceClient();
        UserReadGrpcDTO result = await client.getUserByIdAsync(new GenericMessage { Message = id.ToString() });
        

    }

    public async Task<UserReadDto?> LoginAsync(UserLoginDto dto)
    {
        var client = _grpcService.CreateServiceClient();
        LoginCredentials creds = new LoginCredentials();
        creds.Username = dto.Username;
        creds.Password = dto.Password;
        var result = await client.validateLoginAsync(creds);

        if (result != null)
        {
            return this._mapper.Map<UserReadDto>(result);    
        }
        else
        {
            throw new NullReferenceException();
        }
        
        
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