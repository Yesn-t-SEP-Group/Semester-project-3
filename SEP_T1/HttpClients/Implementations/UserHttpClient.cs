﻿using System.Net.Http.Json;
using System.Text;
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

    public async Task<UserReadDto> GetByIdAsync(int id)
    {
        HttpResponseMessage response = await this._client.GetAsync($"/users/{id}");
        string result = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(result);
        }

        var convert = JsonSerializer.Deserialize<UserReadDto>(result, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true,
        })!;

        return convert;
    }

    public async Task DeleteUser(int id)
    {
        HttpResponseMessage response = await this._client.DeleteAsync($"/users/{id}");
        string result = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(result);
        }
    }
    
    public async Task UpdateAsync(UserUpdateDto dto)
    {
        string dtoAsJson = JsonSerializer.Serialize(dto);
        StringContent body = new StringContent(dtoAsJson, Encoding.UTF8, "application/json");

        HttpResponseMessage response = await _client.PatchAsync("/users", body);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }
}