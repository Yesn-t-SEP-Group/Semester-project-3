using Domain.DTOs;

namespace Application.LogicInterfaces;

public interface IMessagesLogic
{
    Task<MessageReadDto> CreateMessageAsync(MessageCreationDto messageCreateDto);
    Task<IEnumerable<MessageReadDto>>GetMessagesMadeToUserAsync(int userId);
}