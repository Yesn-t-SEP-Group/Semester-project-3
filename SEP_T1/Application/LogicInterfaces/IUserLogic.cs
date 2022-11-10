using Domain.DTOs;
using Domain.Models;

namespace Application.LogicInterfaces;

public interface IUserLogic
{
    Task<UserReadDto> CreateAsync(UserCreationDto user);

    Task<IEnumerable<UserReadDto>> GetAllAsync();

    Task<UserReadDto?> GetByIdAsync(int id);
}