using Application.LogicInterfaces;
using Domain.DTOs;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class RatingsController : ControllerBase
{
    private readonly IRatingLogic ratingLogic;

    public RatingsController(IRatingLogic ratingLogic)
    {
        this.ratingLogic = ratingLogic;
    }
    
    [HttpPost]
    public async Task<ActionResult<RatingReadDto>> CreateRating([FromBody] RatingCreationDto dto)
    {
        try
        {
            RatingReadDto result = await ratingLogic.CreateRatingAsync(dto);
            return Ok(result);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
    
    [HttpGet("{userId:int}")] 
    public async Task<ActionResult<IEnumerable<RatingReadDto>>> GetRatingsMadeToUser([FromRoute] int userId)
    {
        try
        {
            IEnumerable<RatingReadDto> result = await ratingLogic.GetAllRatingsMadeToUser(userId);
            return Ok(result);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
    
    [HttpGet] 
    public async Task<ActionResult<IEnumerable<RatingReadDto>>> GetAllRatings()
    {
        try
        {
            IEnumerable<RatingReadDto> result = await ratingLogic.GetAllRatings();
            return Ok(result);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
    
}