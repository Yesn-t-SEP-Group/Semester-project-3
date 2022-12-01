using Domain.DTOs;

namespace Application.LogicInterfaces;

public interface IReportLogic
{
    Task<IEnumerable<ReportReadDto>> GetAllReportsAsync();
    Task<IEnumerable<ReportReadDto>> GetAllReportsMadeToUserAsync(int id);

    Task CreateReportAsync(ReportCreationDto dto);
}