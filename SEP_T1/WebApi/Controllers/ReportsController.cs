using Application.LogicInterfaces;
using Domain.DTOs;
using Microsoft.AspNetCore.Mvc;

namespace WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class ReportsController : ControllerBase
{
    private readonly IReportLogic reportLogic;

    public ReportsController(IReportLogic reportLogic)
    {
        this.reportLogic = reportLogic;
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<ReportReadDto>>> GetAllReports()
    {
        IEnumerable<ReportReadDto> users = await reportLogic.GetAllReportsAsync();
        return Ok(users);
    }
    
    
    [HttpGet("{IdReportedUser:int}")] 
    public async Task<ActionResult<IEnumerable<ReportReadDto>>> GetReportsMadeToUser([FromRoute] int IdReportedUser)
    {
        try
        {
            IEnumerable<ReportReadDto> result = await reportLogic.GetAllReportsMadeToUserAsync(IdReportedUser);
            return Ok(result);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }

    [HttpPost]
    public async Task<ActionResult<ReportReadDto>> CreateReport([FromBody] ReportCreationDto dto)
    {
        try
        {
            ReportReadDto result = await reportLogic.CreateReportAsync(dto);
            return Ok(result);
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
    [HttpDelete("{reportId:int}")]
    public async Task<ActionResult> DeleteReport([FromRoute] int reportId)
    {
        try
        {
            await reportLogic.DeleteReportAsync(reportId);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
}