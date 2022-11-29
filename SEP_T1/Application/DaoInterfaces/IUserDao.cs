using Domain.DTOs;
using Domain.Models;

namespace Application.DaoInterfaces;

public interface IUserDao
{
    Task<UserReadDto> CreateAsync(UserCreationDto user);

    Task<IEnumerable<UserReadDto>> GetAllAsync();

    Task<UserReadDto?> GetByIdAsync(int id);

    Task DeleteAsync(int id);

    Task<UserReadDto?> LoginAsync(UserLoginDto dto);
    
    Task UpdateAsync(UserUpdateDto dto);
}