using Application.DaoInterfaces;
using Application.LogicInterfaces;
using Domain.DTOs;

namespace Application.Logic;

public class MessageLogic : IMessagesLogic
{
    private readonly IMessagesDao messagesDao;

    public MessageLogic(IMessagesDao messagesDao)
    {
        this.messagesDao = messagesDao;
    }
    
    public async Task<MessageReadDto> CreateMessageAsync(MessageCreationDto messageCreateDto)
    {
        return await messagesDao.CreateMessageAsync(messageCreateDto);
    }

    public async Task<IEnumerable<MessageReadDto>> GetMessagesMadeToUserAsync(int userId)
    {
        return await messagesDao.GetMessagesMadeToUserAsync(userId);
    }
}