using Application.DaoInterfaces;
using Application.LogicInterfaces;
using Domain.DTOs;
using Domain.Models;
using System.ComponentModel.DataAnnotations;
using System.Runtime.Intrinsics.Arm;
using System.Security.Cryptography;
using System.Text;

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
        dto.Password = CalculatePasswordHash(dto.Password);
        UserReadDto created = await userDao.CreateAsync(dto);
        
        return created;
    }

    public Task<IEnumerable<UserReadDto>> GetAllAsync()
    {
        return userDao.GetAllAsync();
    }

    public Task<UserReadDto?> GetByIdAsync(int id)
    {
        return this.userDao.GetByIdAsync(id);
    }

    public Task DeleteAsync(int id)
    {
        return this.userDao.DeleteAsync(id);
    }

    public static string CalculatePasswordHash(string plaintext)
    {
        var sha1 = SHA1.Create();
        var plaintextBytes = Encoding.UTF8.GetBytes(plaintext);
        var hashBytes = sha1.ComputeHash(plaintextBytes);

        var sb = new StringBuilder();
        foreach (var hashByte in hashBytes)
        {
            sb.AppendFormat("{0:x2}", hashByte);
        }

        return sb.ToString();
    }
}