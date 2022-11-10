using System.ComponentModel.DataAnnotations;
using System.Text.Json;
using Application.DaoInterfaces;
using Application.Logic;
using Domain.DTOs;
using Domain.Models;
using FileData;

namespace WebAPI.Services;

public class AuthService : IAuthService
{
    private readonly IUserDao _userDao;

    public AuthService(IUserDao userDao)
    {
        this._userDao = userDao;
    }

    public Task<UserReadDto?> Login(UserLoginDto dto)
    {
        dto.Password = UserLogic.CalculatePasswordHash(dto.Password);
        return _userDao.LoginAsync(dto);
    }
}