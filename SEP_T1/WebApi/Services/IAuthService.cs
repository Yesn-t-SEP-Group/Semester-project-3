using Domain.DTOs;
using Domain.Models;

namespace WebAPI.Services;

public interface IAuthService
{
    Task<UserReadDto?> Login(UserLoginDto dto);
}