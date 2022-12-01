using Application.DaoInterfaces;
using AutoMapper;
using Domain.DTOs;
using Domain.Models;
using Grpc.Net.Client;
using GrpcData.DI;
using Serilog;

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
        var client = this._grpcService.CreateUserServiceClient();
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

    public async Task<IEnumerable<UserReadDto>> GetAllAsync()
    {
        var client = _grpcService.CreateUserServiceClient();

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
        var client = _grpcService.CreateUserServiceClient();
        var result = await client.getUserByIdAsync(new GenericMessage{Message = id.ToString()});

        return this._mapper.Map<UserReadDto>(result);
    }

    public async Task DeleteAsync(int id)
    {
        var client = _grpcService.CreateUserServiceClient();
        var result =  await client.deleteByIdAsync(new GenericMessage{Message = id.ToString()});
        Console.WriteLine(result);
        
        //if (!result.Message.Equals("true"))
        //{
       //     Log.Logger.Error(result.Message);
       //     throw new ArgumentException(result.Message);
       // }
    }

    public async Task<UserReadDto?> LoginAsync(UserLoginDto dto)
    {
        var client = _grpcService.CreateUserServiceClient();
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
    
    public async Task UpdateAsync(UserUpdateDto dto)
    {
        var client = _grpcService.CreateUserServiceClient();
        var mapped = _mapper.Map<UserUpdateGrpcDTO>(dto);
        var result = await client.updateUserInformationAsync(mapped);
    }

    public async Task UpdatePasswordAsync(UserNewPasswordDto newPassword)
    {
        var client = _grpcService.CreateUserServiceClient();
        var grpcConverted=new PasswordUpdateGrpcDTO{Id = newPassword.Id,NewPassword = newPassword.Password};
        var result = await client.updatePasswordAsync(grpcConverted);
    }

    public async Task<IEnumerable<ReportReadDto>> GetAllReportsAsync()
    {
        var client = _grpcService.CreateReportServiceClient();
        var result = await client.getAllReportsAsync(new Empty());
        var mapped = new List<ReportReadDto>();
        foreach (var report in result.Report)
        {
            mapped.Add(_mapper.Map<ReportReadDto>(report));
        }

        return mapped;
    }

    public async Task<IEnumerable<ReportReadDto>> GetAllReportsMadeToUserAsync(int id)
    {
        var client = _grpcService.CreateReportServiceClient();
        var result = await client.getALlReportsMadeToUserAsync(new GenericMessage{Message = id.ToString()});
        var mapped = new List<ReportReadDto>();
        foreach (var report in result.Report)
        {
            mapped.Add(_mapper.Map<ReportReadDto>(report));
        }

        return mapped;
    }
}