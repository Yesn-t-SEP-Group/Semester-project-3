using System.Net.Http.Json;
using System.Text;
using System.Text.Json;
using Domain.DTOs;
using Domain.Models;
using HttpClients.ClientInterfaces;

namespace HttpClients.Implementations;

public class PostHttpClient : IPostService
{
    private readonly HttpClient client;

    public PostHttpClient(HttpClient client)
    {
        this.client = client;
    }
    
    public async Task CreateAsync(PostCreationDto dto)
    {
        HttpResponseMessage response = await client.PostAsJsonAsync("/posts",dto);
        string content = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }
    }

    public async Task<ICollection<PostReadDto>> GetAsync(int? category,int? maxPrice, string? titleContains/*, string? bodyContains*/)
    {
        string query = ConstructQuery(category,maxPrice, titleContains/*, bodyContains*/);

        HttpResponseMessage response = await client.GetAsync("/posts"+query);
        string content = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        ICollection<PostReadDto> posts = JsonSerializer.Deserialize<ICollection<PostReadDto>>(content, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;
        return posts;
    }

    public async Task<PostReadDto> GetByIdAsync(int id)
    {
        HttpResponseMessage response = await client.GetAsync($"/posts/{id}");
        string content = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }

        PostReadDto todo = JsonSerializer.Deserialize<PostReadDto>(content, 
            new JsonSerializerOptions
            {
                PropertyNameCaseInsensitive = true
            }
        )!;
        return todo;
    }

    public async Task UpdateAsync(PostUpdateDto dto)
    {
        string dtoAsJson = JsonSerializer.Serialize(dto);
        StringContent body = new StringContent(dtoAsJson, Encoding.UTF8, "application/json");

        HttpResponseMessage response = await client.PatchAsync("/Posts", body);
        if (!response.IsSuccessStatusCode)
        {
            string content = await response.Content.ReadAsStringAsync();
            throw new Exception(content);
        }
    }

    private static string ConstructQuery(int? category, int? maxPrice, string? titleContains/*, string? bodyContains*/)
    {
        string query = "";
        /*
        if (!string.IsNullOrEmpty(userName))
        {
            query += $"?username={userName}";
        }
        */

        if (category != null)
        {
            query += string.IsNullOrEmpty(query) ? "?" : "&";
            query += $"category={category}";
        }

        if (!string.IsNullOrEmpty(titleContains))
        {
            query += string.IsNullOrEmpty(query) ? "?" : "&";
            query += $"titlecontains={titleContains}";
        }
        if (maxPrice != null)
        {
            query += string.IsNullOrEmpty(query) ? "?" : "&";
            query += $"maxprice={maxPrice}";
        }
        
/* (!string.IsNullOrEmpty(bodyContains))
        {
            query += string.IsNullOrEmpty(query) ? "?" : "&";
            query += $"bodycontains={bodyContains}";
        }*/

        return query;
    }
    
    public async Task DeleteAsync(int id)
    {
        HttpResponseMessage response = await client.DeleteAsync($"Posts/{id}");
        string content = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(content);
        }
    }

    public async Task<CategoryReadDto> GetPostCategoryAsync(int postId)
    {
        HttpResponseMessage response = await client.GetAsync($"/categories/{postId}");
        string result = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(result);
        }

        var convert = JsonSerializer.Deserialize<CategoryReadDto>(result, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true,
        })!;

        return convert;

    }

    public async Task<CategoryReadDto> CreateCategoryAsync(string description)
    {
        HttpResponseMessage response = await client.PostAsJsonAsync("/categories", description);
        string result = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(result);
        }

        var convert = JsonSerializer.Deserialize<CategoryReadDto>(result, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;

        return convert;
    }

    public async Task<IEnumerable<CategoryReadDto>> GetAllCategoriesAsync()
    {
        HttpResponseMessage response = await client.GetAsync("/categories");
        string result = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(result);
        }

        var convert = JsonSerializer.Deserialize<ICollection<CategoryReadDto>>(result, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true,
        })!;

        return convert;
    }

    public async Task DeleteCategoryAsync(int categoryId)
    {
        HttpResponseMessage response = await client.DeleteAsync($"/categories/{categoryId}");
        string result = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(result);
        }
    }
}