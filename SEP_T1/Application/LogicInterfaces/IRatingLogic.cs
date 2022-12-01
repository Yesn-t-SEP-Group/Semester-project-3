using Domain.DTOs;

namespace Application.LogicInterfaces;

public interface IRatingLogic
{
    Task<RatingReadDto> CreateRatingAsync(RatingCreationDto dto);
    Task<IEnumerable<RatingReadDto>> GetAllRatingsMadeToUser(int id);
    Task<IEnumerable<RatingReadDto>> GetAllRatings();

}