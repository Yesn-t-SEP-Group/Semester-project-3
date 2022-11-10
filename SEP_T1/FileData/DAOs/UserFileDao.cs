using Application.DaoInterfaces;
using Domain.DTOs;
using Domain.Models;

namespace FileData.DAOs;

public class UserFileDao : IUserDao
{
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

    public Task<User?> GetByIdAsync(int id)
    {
        User? existing = context.Users.FirstOrDefault(u =>
            u.Id == id
        );
        return Task.FromResult(existing);
    }

    Task<UserReadDto?> IUserDao.GetByUsernameAsync(string userName)
    {
        throw new NotImplementedException();
    }

    public Task<UserReadDto> CreateAsync(UserCreationDto user)
    {
        throw new NotImplementedException();
    }

    public Task<IEnumerable<UserReadDto>> GetAllAsync()
    {
        throw new NotImplementedException();
    }

    Task<UserReadDto?> IUserDao.GetByIdAsync(int id)
    {
        throw new NotImplementedException();
    }
}