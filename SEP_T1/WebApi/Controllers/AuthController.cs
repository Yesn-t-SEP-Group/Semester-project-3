using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using Application.LogicInterfaces;
using Domain.DTOs;
using Domain.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using WebAPI.Services;
using JwtRegisteredClaimNames = Microsoft.IdentityModel.JsonWebTokens.JwtRegisteredClaimNames;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class AuthController : ControllerBase
{
    private readonly IConfiguration config;
    private readonly IAuthService authService;
    private readonly IUserLogic userLogic;

    public AuthController(IConfiguration config, IAuthService authService, IUserLogic userLogic)
    {
        this.config = config;
        this.authService = authService;
        this.userLogic = userLogic;
    }

    private List<Claim> GenerateClaims(UserReadDto user)
    {
        var claims = new[]
        {
            new Claim(JwtRegisteredClaimNames.Sub, config["Jwt:Subject"]),
            new Claim(JwtRegisteredClaimNames.Jti, Guid.NewGuid().ToString()),
            new Claim(JwtRegisteredClaimNames.Iat, DateTime.UtcNow.ToString()),
            new Claim(ClaimTypes.Role, user.Role),
            new Claim("Role",user.Role),
            new Claim("Username",user.UserName),
            new Claim("Id",user.Id.ToString()),
            new Claim("Name",user.Name),
            new Claim("Email",user.Email),
            new Claim("PhoneNumber",user.PhoneNumber),
            new Claim("Address",user.Address),
            new Claim("Rating",user.Rating.ToString()),
        };
        return claims.ToList();
    }
    
    private string GenerateJwt(UserReadDto user)
    {
        List<Claim> claims = GenerateClaims(user);
    
        SymmetricSecurityKey key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(config["Jwt:Key"]));
        SigningCredentials signIn = new SigningCredentials(key, SecurityAlgorithms.HmacSha512);
    
        JwtHeader header = new JwtHeader(signIn);
    
        JwtPayload payload = new JwtPayload(
            config["Jwt:Issuer"],
            config["Jwt:Audience"],
            claims, 
            null,
            DateTime.UtcNow.AddMinutes(60));
    
        JwtSecurityToken token = new JwtSecurityToken(header, payload);
    
        string serializedToken = new JwtSecurityTokenHandler().WriteToken(token);
        return serializedToken;
    }
    
    [HttpPost, Route("login")]
    public async Task<ActionResult> Login([FromBody] UserLoginDto userLoginDto)
    {
        try
        {
            UserReadDto? user = await authService.Login(userLoginDto);
            if (user == null)
            {
                return BadRequest();
            }
            string token = GenerateJwt(user);

            return Ok(token);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
    
    [HttpPatch("{userId:int}")]
    public async Task<ActionResult> UpdatePassword([FromBody] String newPassword,int userId)
    {
        try
        {
            var dto = new UserNewPasswordDto { Id = userId, Password = newPassword };
            await userLogic.UpdatePassword(dto);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
}