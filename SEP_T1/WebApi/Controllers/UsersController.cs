﻿using Application.LogicInterfaces;
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

    [HttpDelete("{userId:int}")]
    public async Task<ActionResult> Delete(int userId)
    {
        await userLogic.DeleteAsync(userId);
        
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
    
    [HttpPatch]
    public async Task<ActionResult> UpdateAsync([FromBody] UserUpdateDto dto)
    {
        try
        {
            await userLogic.UpdateAsync(dto);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
    
    
    [HttpGet("{userId:int}")]
    public async Task<ActionResult<UserReadDto>> GetUserById([FromRoute] int userId)
    {
        try
        {
            UserReadDto? user= await userLogic.GetByIdAsync(userId);
            return Ok(user);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
        
    }
}