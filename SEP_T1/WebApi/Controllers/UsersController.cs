using Application.LogicInterfaces;
using Domain.DTOs;
using Domain.Models;
using Microsoft.AspNetCore.Mvc;
using Serilog;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class UsersController : ControllerBase
{
    private readonly IUserLogic userLogic;

    public UsersController(IUserLogic userLogic)
    {
        this.userLogic = userLogic;
    }

    [HttpPost]
    public async Task<ActionResult<User>> CreateAsync(UserCreationDto dto)
    {
        try
        {
            UserReadDto user = await userLogic.CreateAsync(dto);
            return Created($"/users/{user.Id}", user);
        }
        catch (Exception e)
        {
            Log.Logger.Error(e.Message);
            return StatusCode(500, e.Message);
        }
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<UserReadDto>>> Get()
    {
        IEnumerable<UserReadDto> users = await userLogic.GetAllAsync();
        return Ok(users);
    }

    [HttpDelete("{id}")]
    public async Task<ActionResult> Delete(int id)
    {
        await userLogic.DeleteAsync(id);
        
        /*
        try
        {
            await userLogic.DeleteAsync(id);
        }
        catch (Exception e)
        {
            Log.Logger.Error(e.Message);
            return StatusCode(500, e.Message);
        }
*/

        return Ok();
    }
}