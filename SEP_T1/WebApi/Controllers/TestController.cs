﻿using System.ComponentModel;
using Application.LogicInterfaces;
using Domain.DTOs;
using Domain.Models;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class TestController : ControllerBase
{
    private readonly IUserLogic userLogic;
    private readonly IReportLogic reportLogic;
    private readonly IPostLogic postLogic;
    private readonly IRatingLogic ratingLogic;

    public TestController(IUserLogic userLogic, IReportLogic reportLogic, IPostLogic postLogic, IRatingLogic ratingLogic)
    {
        this.userLogic = userLogic;
        this.reportLogic = reportLogic;
        this.postLogic = postLogic;
        this.ratingLogic = ratingLogic;
    }
    
    [HttpGet]
    [Route("postTests/GettingPostWithIdReturnsCorrectId/{postId:int}")]
    public async Task<ActionResult> GettingPostWithIdReturnsCorrectId([FromRoute] int postId)
    {
        try
        {
            PostReadDto result = await postLogic.GetByIdAsync(postId);
            if (result.Id==postId)
            {
                return Ok();
            }

            throw new ArgumentException("Test failed!");
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
    [HttpGet]
    [Route("userTests/GettingUserWithIdReturnsCorrectId/{userId:int}")]
    public async Task<ActionResult> GettingUserWithIdReturnsCorrectId([FromRoute] int userId)
    {
        try
        {
            UserReadDto? user= await userLogic.GetByIdAsync(userId);
            if (user != null && user.Id==userId)
            {
                return Ok();
            }
            throw new ArgumentException("Test failed!");
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
        
    }
}