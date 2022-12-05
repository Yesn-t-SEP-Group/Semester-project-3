using Application.DaoInterfaces;
using Application.LogicInterfaces;
using Domain.DTOs;

namespace Application.Logic;

public class ReportLogic : IReportLogic
{
    private readonly IReportDao reportDao;

    public ReportLogic(IReportDao reportDao)
    {
        this.reportDao = reportDao;
    }
    
    public async Task<IEnumerable<ReportReadDto>> GetAllReportsAsync()
    {
        return await reportDao.GetAllReportsAsync();
    }

    public async Task<IEnumerable<ReportReadDto>> GetAllReportsMadeToUserAsync(int id)
    {
        return await reportDao.GetAllReportsMadeToUserAsync(id);
    }

    public async Task<ReportReadDto> CreateReportAsync(ReportCreationDto dto)
    {
       return await reportDao.CreateReportAsync(dto);
    }

    public async Task DeleteReportAsync(int id)
    {
         await reportDao.DeleteReportAsync(id);
    }
}