using Domain.DTOs;

namespace HttpClients.ClientInterfaces;

public interface IReportService
{
    public  Task<IEnumerable<ReportReadDto>> GetAllReports();

    public Task<IEnumerable<ReportReadDto>> GetReportsMadeToUser(int UserReportedId);

    public Task<ReportReadDto> CreateReport(ReportCreationDto dto);

    public Task DeleteReport(int id);

}