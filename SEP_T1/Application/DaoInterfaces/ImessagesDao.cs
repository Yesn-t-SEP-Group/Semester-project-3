using Domain.DTOs;

namespace Application.DaoInterfaces;

public interface IMessagesDao
{
    Task<MessageReadDto> CreateMessageAsync(MessageCreationDto messageCreateDto);
    Task<IEnumerable<MessageReadDto>>GetMessagesMadeToUserAsync(int userId);
}