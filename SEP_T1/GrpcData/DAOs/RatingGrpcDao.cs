using Application.DaoInterfaces;
using AutoMapper;
using Domain.DTOs;
using GrpcData.DI;

namespace GrpcData.DAOs;

public class RatingGrpcDao : IRatingDao
{
    private readonly IGrpcService _grpcService;
    private readonly IMapper _mapper;

    public RatingGrpcDao(
        IGrpcService grpcService,
        IMapper mapper)
    {
        this._grpcService = grpcService;
        _mapper = mapper;
    }
    
    
    public async Task<RatingReadDto> CreateRatingAsync(RatingCreationDto dto)
    {
        var client = _grpcService.CreateRatingServiceClient();
        var mapped  =_mapper.Map<RatingCreationGrpcDto>(dto);
        var result = await client.createRatingAsync(mapped);
        return _mapper.Map<RatingReadDto>(result);
    }

    public async Task<IEnumerable<RatingReadDto>> GetAllRatingsMadeToUser(int id)
    {
        var client = _grpcService.CreateRatingServiceClient();
        var result = await client.getAllRatingsMadeToUserAsync(new GenericMessage{Message = id.ToString()});
        List<RatingReadDto> mapped = new List<RatingReadDto>();
        foreach (var rating in result.Rating)
        {
           mapped.Add(_mapper.Map<RatingReadDto>(rating));  
        }

        return mapped;
    }

    public async Task<IEnumerable<RatingReadDto>> GetAllRatingsAsync()
    {
        var client = _grpcService.CreateRatingServiceClient();
        var result = await client.getAllRatingsAsync(new Empty());
        List<RatingReadDto> mapped = new List<RatingReadDto>();
        foreach (var rating in result.Rating)
        {
            mapped.Add(_mapper.Map<RatingReadDto>(rating));  
        }
        return mapped;
    }
}