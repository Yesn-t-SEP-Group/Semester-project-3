using System.Net.Http.Json;
using System.Text.Json;
using Domain.DTOs;
using Domain.Models;
using HttpClients.ClientInterfaces;

namespace HttpClients.Implementations;

public class UserHttpClient : IUserService
{
    private readonly HttpClient _client;


    public UserHttpClient(HttpClient client)
    {
        this._client = client;
    }

    public async Task<UserReadDto> Create(UserCreationDto dto)
    {
        HttpResponseMessage response = await this._client.PostAsJsonAsync("/users", dto);
        string result = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(result);
        }

        var convert = JsonSerializer.Deserialize<UserReadDto>(result, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;

        return convert;
    }
    
    

    public async Task<IEnumerable<UserReadDto>> GetUsers()
    {
        /*
        var moq = new List<UserReadDto>()
            {
                new UserReadDto() { UserName = "Test", Id = 1, RegistrationDateTime = DateTime.Now },
                new UserReadDto() { UserName = "Test2", Id = 2, RegistrationDateTime = DateTime.Now },
                new UserReadDto() { UserName = "Test3", Id = 3, RegistrationDateTime = DateTime.Now },
            };

        string jsonString = JsonSerializer.Serialize(moq);


        return JsonSerializer.Deserialize<IEnumerable<UserReadDto>>(jsonString, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true,
        })!;
        */

        HttpResponseMessage response = await this._client.GetAsync("/users");
        string result = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(result);
        }

        var convert = JsonSerializer.Deserialize<IEnumerable<UserReadDto>>(result, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true,
        })!;

        return convert;
    }

    public async Task DeleteUser(int id)
    {
        HttpResponseMessage response = await this._client.GetAsync($"/users/{id}/delete");
        string result = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(result);
        }
    }
}