using Application.DaoInterfaces;
using Application.LogicInterfaces;
using Domain.DTOs;

namespace Application.Logic;

public class RatingLogic : IRatingLogic
{
    private readonly IRatingDao ratingDao;

    public RatingLogic(IRatingDao ratingDao)
    {
        this.ratingDao = ratingDao;
    }

    public async Task<RatingReadDto> CreateRatingAsync(RatingCreationDto dto)
    {
        return await ratingDao.CreateRatingAsync(dto);
    }

    public async Task<IEnumerable<RatingReadDto>> GetAllRatingsMadeToUser(int id)
    {
        return await ratingDao.GetAllRatingsMadeToUser(id);
    }

    public async Task<IEnumerable<RatingReadDto>> GetAllRatings()
    {
        return await ratingDao.GetAllRatingsAsync();
    }
}