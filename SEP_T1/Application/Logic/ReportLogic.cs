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

    public async Task CreateReportAsync(ReportCreationDto dto)
    {
        await reportDao.CreateReportAsync(dto);
    }
}