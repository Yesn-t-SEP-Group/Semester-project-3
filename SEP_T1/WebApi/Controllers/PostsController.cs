using Application.Logic;
using Application.LogicInterfaces;
using Domain.DTOs;
using Domain.Models;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class PostsController : ControllerBase
{

    private readonly IPostLogic _postLogic;

    public PostsController(IPostLogic postLogic)
    {
        this._postLogic = postLogic;
    }

    [HttpPost]
    public async Task<ActionResult<Post>> CreateAsync(PostCreationDto dto)
    {
        Console.WriteLine();
        try
        {
            PostReadDto created = await _postLogic.CreateAsync(dto);
            return Created($"/posts/{created.Id}", created);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

     /*   
    [HttpGet]
    public async Task<ActionResult<IEnumerable<PostReadDto>>> GetAsync()
    {
        try
        {
            var todos = await _postLogic.GetAsync();
            return Ok(todos);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
    */
    
    [HttpGet]
    public async Task<ActionResult<IEnumerable<PostReadDto>>> GetAsync([FromQuery] int? category,
        [FromQuery] string? titleContains,[FromQuery] int? maxPrice/*, [FromQuery] string? bodyContains*/)
    {
        try
        {
            SearchPostParametersDto parameters = new( category, titleContains, maxPrice/*, bodyContains*/);
            var posts = await _postLogic.GetAsync(parameters);
            return Ok(posts);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

    [HttpPatch]
    public async Task<ActionResult> UpdateAsync([FromBody] PostUpdateDto dto)
    {
        try
        {
            await _postLogic.UpdateAsync(dto);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

    [HttpDelete("{postId:int}")]
    public async Task<ActionResult> DeleteAsync([FromRoute] int postId)
    {
        try
        {
            await _postLogic.DeleteAsync(postId);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

    [HttpGet("{postId:int}")]
    public async Task<ActionResult<PostReadDto>> GetById([FromRoute] int postId)
    {
        try
        {
            PostReadDto result = await _postLogic.GetByIdAsync(postId);
            return Ok(result);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

    [HttpGet]
    [Route("GetOwner{postId:int}")]
    public async Task<ActionResult<UserReadDto>> GetOwnerDetails([FromRoute] int postId)
    {
        try
        {
            UserReadDto result = await _postLogic.GetPostOwner(postId);
            return Ok(result);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
}
