using Domain.DTOs;

namespace Application.DaoInterfaces;

public interface IRatingDao
{
    Task<RatingReadDto> CreateRatingAsync(RatingCreationDto dto);
    Task<IEnumerable<RatingReadDto>> GetAllRatingsMadeToUser(int id);
    Task<IEnumerable<RatingReadDto>> GetAllRatingsAsync();
}