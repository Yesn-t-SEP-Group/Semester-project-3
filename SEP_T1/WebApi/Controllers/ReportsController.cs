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
    
    
    [HttpGet("{id:int}")] 
    public async Task<ActionResult<IEnumerable<ReportReadDto>>> GetReportsMadeToUser([FromRoute] int id)
    {
        try
        {
            IEnumerable<ReportReadDto> result = await reportLogic.GetAllReportsMadeToUserAsync(id);
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
    [HttpDelete("{id:int}")]
    public async Task<ActionResult> DeleteReport([FromRoute] int id)
    {
        try
        {
            await reportLogic.DeleteReportAsync(id);
            return Ok();
        }
        catch (Exception e)
        {
            Console.WriteLine(e);
            return StatusCode(500, e.Message);
        }
    }
}