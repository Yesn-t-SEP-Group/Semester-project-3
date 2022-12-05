using Application.LogicInterfaces;
using Domain.DTOs;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class CategoriesController : ControllerBase
{
    
    private readonly IPostLogic _postLogic;

    public CategoriesController(IPostLogic postLogic)
    {
        this._postLogic = postLogic;
    }

    [HttpGet("{postId:int}")] 
    public async Task<ActionResult<CategoryReadDto>> GetCategoryDetails([FromRoute] int postId)
    {
        try
        {
            CategoryReadDto result = await _postLogic.GetPostCategoryAsync(postId);
            return Ok(result);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

    [HttpPost]
    public async Task<ActionResult<CategoryReadDto>> CreateCategory([FromBody] string description)
    {
        try
        {
            CategoryReadDto result = await _postLogic.CreateCategoryAsync(description);
            return Ok(result);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<CategoryReadDto>>> GetAllCategories()
    {
        try
        {
            IEnumerable<CategoryReadDto> result = await _postLogic.GetAllCategoriesAsync();
            return Ok(result);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

}