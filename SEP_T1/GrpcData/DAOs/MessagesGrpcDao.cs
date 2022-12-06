using Application.DaoInterfaces;
using AutoMapper;
using Domain.DTOs;
using GrpcData.DI;

namespace GrpcData.DAOs;

public class MessagesGrpcDao : IMessagesDao
{
    private readonly IGrpcService _grpcService;
    private readonly IMapper _mapper;

    public MessagesGrpcDao(
        IGrpcService grpcService,
        IMapper mapper)
    {
        this._grpcService = grpcService;
        _mapper = mapper;
    }

    /// <summary>
    /// Asynchronously creates a new private message.
    /// </summary>
    /// <param name="messageCreateDto">The message creation data transfer object (DTO).</param>
    /// <returns>The created message read DTO.</returns>
    public async Task<MessageReadDto> CreateMessageAsync(MessageCreationDto messageCreateDto)
    {
        // Create a client for the private message service
        var client = _grpcService.CreatePrivateMessageServiceClient();

        // Map the message creation DTO to a CreateDMGrpcDto object
        var request = _mapper.Map<CreateDMGrpcDto>(messageCreateDto);

        // Create the new message
        var response = await client.CreateMessageAsync(request);

        // Map the response to a MessageReadDto object and return it
        return _mapper.Map<MessageReadDto>(response); 
    }

    
    public async Task<IEnumerable<MessageReadDto>> GetMessagesMadeToUserAsync(int userId)
    {
        // Create a client for the private message service
        var client = _grpcService.CreatePrivateMessageServiceClient();

        // Get the messages made to the specified user
        var response = await client.GetMessageMadeToUserAsync(new GenericMessage { Message = userId.ToString() });

        // Map the messages to MessageReadDto objects
        var mappedList = response.Messages.Select(m => _mapper.Map<MessageReadDto>(m)).ToList();

        // Return the list of mapped messages
        return mappedList;
    }

}