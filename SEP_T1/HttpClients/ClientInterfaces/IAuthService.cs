using Domain.DTOs;
using Domain.Models;

namespace HttpClients.ClientInterfaces;

using System.Security.Claims;

public interface IAuthService
{
    public Task LoginAsync(UserLoginDto dto);
    public Task LogoutAsync();
    public Task RegisterAsync(UserCreationDto userCreationDto);
    public Task<ClaimsPrincipal> GetAuthAsync();

    public Action<ClaimsPrincipal> OnAuthStateChanged { get; set; }
}