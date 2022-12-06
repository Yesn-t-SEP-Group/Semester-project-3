using Application.LogicInterfaces;
using Domain.DTOs;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers;


[ApiController]
[Route("[controller]")]
public class MessagesController : ControllerBase
{
    private readonly IMessagesLogic _messagesLogic;

    public MessagesController(IMessagesLogic messagesLogic)
    {
        this._messagesLogic = messagesLogic;
    }
    
    [HttpGet("{userId:int}")]
    public async Task<ActionResult<IEnumerable<MessageReadDto>>> GetMessagesMadeToUser([FromRoute] int userId)
    {
        try
        {
            var messages = await _messagesLogic.GetMessagesMadeToUserAsync(userId);
            return Ok(messages);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
    [HttpPost]
    public async Task<ActionResult<IEnumerable<MessageReadDto>>> GetMessagesMadeToUser([FromBody] MessageCreationDto dto)
    {
        try
        {
            var message = await _messagesLogic.CreateMessageAsync(dto);
            return Ok(message);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
}