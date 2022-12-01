using Application.DaoInterfaces;
using Application.Logic;
using AutoMapper;
using Domain.DTOs;
using Domain.Models;

namespace FileData.DAOs;

public class UserFileDao : IUserDao
{
    private readonly FileContext context;
    private readonly IMapper _mapper;

    public UserFileDao(
        FileContext context,
        IMapper mapper)
    {
        this.context = context;
        this._mapper = mapper;
    }

    public Task<UserReadDto> CreateAsync(UserCreationDto user)
    {
        int userId = 1;
        if (context.Users.Any())
        {
            userId = context.Users.Max(u => u.Id);
            userId++;
        }

        User u = this._mapper.Map<User>(user);
        u.Id = userId;

        return Task.Run(() =>
        {
            context.Users.Add(u);
            context.SaveChanges();

            return this._mapper.Map<UserReadDto>(u);
        });
    }

    public Task<IEnumerable<UserReadDto>> GetAllAsync()
    {
        return Task.Run(() =>
        {
            return this.context.Users.Select(x=> this._mapper.Map<UserReadDto>(x));
        });
    }

    Task<UserReadDto?> IUserDao.GetByIdAsync(int id)
    {
        return Task.Run(() =>
        {
            User? existing = context.Users.FirstOrDefault(u =>
                u.Id == id
            );

            if (existing == null)
            {
                return null;
            }

            return this._mapper.Map<UserReadDto>(existing);
        });
    }

    public Task DeleteAsync(int id)
    {
        return Task.Run(() =>
        {
            var user = context.Users.FirstOrDefault(x=>x.Id== id);
            if(user != null)
            {
                context.Users.Remove(user);
                context.SaveChanges();
            }
        });
    }

    public Task<UserReadDto?> LoginAsync(UserLoginDto dto)
    {
        return Task.Run(() =>
        {
            var user = context.Users.FirstOrDefault(x => x.UserName == dto.Username && x.Password == dto.Password);
            if (user == null)
            {
                return null;
            }

            return this._mapper.Map<UserReadDto>(user);
        });
    }

    public Task UpdateAsync(UserUpdateDto dto)
    {
        throw new NotImplementedException();
    }

    public Task UpdatePasswordAsync(UserNewPasswordDto newPassword)
    {
        throw new NotImplementedException();
    }

    public Task<IEnumerable<ReportReadDto>> GetAllReportsAsync()
    {
        throw new NotImplementedException();
    }

    public Task<IEnumerable<ReportReadDto>> GetAllReportsMadeToUserAsync(int id)
    {
        throw new NotImplementedException();
    }
}