using Domain.DTOs;

namespace HttpClients.ClientInterfaces;

public interface IMessageService
{
    Task<MessageReadDto> CreateMessageAsync(MessageCreationDto messageCreateDto);
    Task<IEnumerable<MessageReadDto>>GetMessagesMadeToUserAsync(int userId);
}