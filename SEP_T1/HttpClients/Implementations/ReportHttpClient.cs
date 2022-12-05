using System.Net.Http.Json;
using System.Text.Json;
using Domain.DTOs;
using HttpClients.ClientInterfaces;

namespace HttpClients.Implementations;

public class ReportHttpClient: IReportService
{
    private readonly HttpClient _client;


    public ReportHttpClient(HttpClient client)
    {
        this._client = client;
    }
    
    public async Task<IEnumerable<ReportReadDto>> GetAllReports()
    {
        HttpResponseMessage response = await this._client.GetAsync("/reports");
        string result = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(result);
        }

        var convert = JsonSerializer.Deserialize<IEnumerable<ReportReadDto>>(result, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true,
        })!;

        return convert;
    }

    public async Task<IEnumerable<ReportReadDto>> GetReportsMadeToUser(int UserReportedId)
    {
        HttpResponseMessage response = await this._client.GetAsync($"/reports/{UserReportedId}");
        string result = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(result);
        }

        var convert = JsonSerializer.Deserialize<IEnumerable<ReportReadDto>>(result, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true,
        })!;

        return convert;
    }

    public async Task<ReportReadDto> CreateReport(ReportCreationDto dto)
    {
        HttpResponseMessage response = await this._client.PostAsJsonAsync("/reports", dto);
        string result = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(result);
        }

        var convert = JsonSerializer.Deserialize<ReportReadDto>(result, new JsonSerializerOptions
        {
            PropertyNameCaseInsensitive = true
        })!;

        return convert;
    }

    public async Task DeleteReport(int id)
    {
        HttpResponseMessage response = await this._client.DeleteAsync($"/reports/{id}");
        string result = await response.Content.ReadAsStringAsync();
        if (!response.IsSuccessStatusCode)
        {
            throw new Exception(result);
        }
    }
}