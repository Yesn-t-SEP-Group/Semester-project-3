using System.Net.Http.Json;
using System.Text.Json;
using Domain.DTOs;
using HttpClients.ClientInterfaces;

namespace HttpClients.Implementations;

public class MessageHttpClient : IMessageService
{
    private readonly HttpClient client;

    public MessageHttpClient(HttpClient client)
    {
        this.client = client;
    }
    
    public async Task<MessageReadDto> CreateMessageAsync(MessageCreationDto messageCreateDto)
    {
        HttpResponseMessage response = await client.PostAsJsonAsync("/messages",messageCreateDto);
        string content = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }
        return JsonSerializer.Deserialize<MessageReadDto>(content);
    }

    public async Task<IEnumerable<MessageReadDto>> GetMessagesMadeToUserAsync(int userId)
    {
        HttpResponseMessage response = await client.GetAsync($"/messages/{userId}");
        string content = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        ICollection<MessageReadDto> posts = JsonSerializer.Deserialize<ICollection<MessageReadDto>>(content, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;
        return posts;
    }
}