using Application.DaoInterfaces;
using AutoMapper;
using Domain.DTOs;
using GrpcData.DI;

namespace GrpcData.DAOs;

public class ReportGrpcDao : IReportDao
{
    private readonly IGrpcService _grpcService;
    private readonly IMapper _mapper;

    public ReportGrpcDao(
        IGrpcService grpcService,
        IMapper mapper)
    {
        this._grpcService = grpcService;
        _mapper = mapper;
    }
    
    public async Task<IEnumerable<ReportReadDto>> GetAllReportsAsync()
    {
        var client = _grpcService.CreateReportServiceClient();
        var result = await client.getAllReportsAsync(new Empty());
        var mapped = new List<ReportReadDto>();
        foreach (var report in result.Report)
        {
            mapped.Add(_mapper.Map<ReportReadDto>(report));
        }

        return mapped;
    }

    public async Task<IEnumerable<ReportReadDto>> GetAllReportsMadeToUserAsync(int id)
    {
        var client = _grpcService.CreateReportServiceClient();
        var result = await client.getALlReportsMadeToUserAsync(new GenericMessage{Message = id.ToString()});
        var mapped = new List<ReportReadDto>();
        foreach (var report in result.Report)
        {
            mapped.Add(_mapper.Map<ReportReadDto>(report));
        }
        return mapped;
    }

    public async Task<ReportReadDto> CreateReportAsync(ReportCreationDto dto)
    {
        var client = _grpcService.CreateReportServiceClient();
        var result= await client.reportUserAsync(_mapper.Map<ReportCreationGrpcDto>(dto));
        return _mapper.Map<ReportReadDto>(result);
    }

    public async Task DeleteReportAsync(int id)
    {
        var client = _grpcService.CreateReportServiceClient();
        await client.deleteReportAsync(new GenericMessage { Message = id.ToString() });
    }
}