using Application.DaoInterfaces;
using Application.Logic;
using Domain.DTOs;
using Domain.Mappings;
using Domain.Models;

namespace FileData.DAOs;

public class UserFileDao : IUserDao
{
    private readonly FileContext context;

    public UserFileDao(FileContext context)
    {
        this.context = context;
    }

    public Task<UserReadDto> CreateAsync(UserCreationDto user)
    {
        int userId = 1;
        if (context.Users.Any())
        {
            userId = context.Users.Max(u => u.Id);
            userId++;
        }

        User u = UserMapping.UserFromUserCreationDto(user);
        u.Id = userId;

        return Task.Run(() =>
        {
            context.Users.Add(u);
            context.SaveChanges();

            return UserMapping.UserReadDtoFromUser(u);
        });
    }

    public Task<IEnumerable<UserReadDto>> GetAllAsync()
    {
        return Task.Run(() =>
        {
            return this.context.Users.Select(x=> UserMapping.UserReadDtoFromUser(x));
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

            return UserMapping.UserReadDtoFromUser(existing);
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

            return UserMapping.UserReadDtoFromUser(user);
        });
    }
}