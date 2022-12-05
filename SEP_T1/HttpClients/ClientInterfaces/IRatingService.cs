using Domain.DTOs;

namespace HttpClients.ClientInterfaces;

public interface IRatingService
{
    public Task<RatingReadDto> CreateRating(RatingCreationDto dto);

    public Task<IEnumerable<RatingReadDto>> GetRatingsMadeToUser(int id);

    public Task<IEnumerable<RatingReadDto>> GetAllRatings();
}