using Domain.DTOs;

namespace Application.LogicInterfaces;

public interface IReportLogic
{
    Task<IEnumerable<ReportReadDto>> GetAllReportsAsync();
    Task<IEnumerable<ReportReadDto>> GetAllReportsMadeToUserAsync(int id);

    Task<ReportReadDto> CreateReportAsync(ReportCreationDto dto);
    Task DeleteReportAsync(int id);
}