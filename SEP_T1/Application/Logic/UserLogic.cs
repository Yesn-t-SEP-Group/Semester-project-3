using Application.DaoInterfaces;
using Application.LogicInterfaces;
using Domain.DTOs;
using Domain.Models;
using System.ComponentModel.DataAnnotations;

namespace Application.Logic;

public class UserLogic : IUserLogic
{
    private readonly IUserDao userDao;

    public UserLogic(IUserDao userDao)
    {
        this.userDao = userDao;
    }

    public async Task<UserReadDto> CreateAsync(UserCreationDto dto)
    {
        UserReadDto? existing = await userDao.GetByUsernameAsync(dto.UserName);
        if (existing != null)
            throw new ValidationException("Username already taken!");

        ValidateData(dto);
        UserCreationDto toCreate = new UserCreationDto()
        {
            UserName = dto.UserName,
            Password = dto.Password,
            Role = dto.Role,
            Name = dto.Name,
            Email = dto.Email,
            Address = dto.Address,
            PhoneNumber = dto.PhoneNumber,
        };
        
        UserReadDto created = await userDao.CreateAsync(toCreate);
        
        return created;
    }

    public Task<IEnumerable<UserReadDto>> GetAllAsync()
    {
        return userDao.GetAllAsync();
    }

    private static void ValidateData(UserCreationDto userToCreate)
    {
        string userName = userToCreate.UserName;
        string passWord = userToCreate.Password;

        if (userName.Length < 3)
            throw new Exception("Username must be at least 3 characters!");

        if (userName.Length > 15)
            throw new Exception("Username must be less than 16 characters!");
        
        if (passWord.Length < 5)
            throw new Exception("Password must be at least 5 characters!");

        if (passWord.Length > 15)
            throw new Exception("Password must be less than 16 characters!");
    }

    public Task<UserReadDto?> GetByIdAsync(int id)
    {
        return userDao.GetByIdAsync(id);
    }
}