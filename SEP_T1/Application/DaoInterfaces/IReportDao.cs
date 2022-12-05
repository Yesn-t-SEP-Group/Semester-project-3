using Domain.DTOs;

namespace Application.DaoInterfaces;

public interface IReportDao
{
    Task<IEnumerable<ReportReadDto>> GetAllReportsAsync();
    Task<IEnumerable<ReportReadDto>> GetAllReportsMadeToUserAsync(int id);
    
    Task<ReportReadDto> CreateReportAsync(ReportCreationDto dto);
    Task DeleteReportAsync(int id);
}