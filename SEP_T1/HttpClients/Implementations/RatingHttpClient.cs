using System.Net.Http.Json;
using System.Text.Json;
using Domain.DTOs;
using HttpClients.ClientInterfaces;

namespace HttpClients.Implementations;

public class RatingHttpClient : IRatingService
{
    
    private readonly HttpClient _client;

    public RatingHttpClient(HttpClient client)
    {
        this._client = client;
    }
    
    public async Task<RatingReadDto> CreateRating(RatingCreationDto dto)
    {
        HttpResponseMessage response = await this._client.PostAsJsonAsync("/ratings", dto);
        string result = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(result);
        }

        var convert = JsonSerializer.Deserialize<RatingReadDto>(result, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;

        return convert;
    }

    public async Task<IEnumerable<RatingReadDto>> GetRatingsMadeToUser(int id)
    {
        HttpResponseMessage response = await this._client.GetAsync($"/ratings/{id}");
        string result = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(result);
        }

        var convert = JsonSerializer.Deserialize<IEnumerable<RatingReadDto>>(result, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true,
        })!;

        return convert;
        
    }

    public async Task<IEnumerable<RatingReadDto>> GetAllRatings()
    {
        HttpResponseMessage response = await this._client.GetAsync($"/ratings");
        string result = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(result);
        }

        var convert = JsonSerializer.Deserialize<IEnumerable<RatingReadDto>>(result, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true,
        })!;

        return convert;
    }
}