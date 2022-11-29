using Domain.DTOs;
using Domain.Models;

namespace HttpClients.ClientInterfaces;

public interface IUserService
{
    Task<UserReadDto> Create(UserCreationDto dto);

    Task<IEnumerable<UserReadDto>> GetUsers();

    Task DeleteUser(int id);
    
    Task UpdateAsync(UserUpdateDto dto);
    
}