namespace Domain.DTOs;

public class RatingReadDto
{
    public int RatingId { get; set; }
    public int UserFromId { get; set; }
    public int UserToId { get; set; }
    public int RatingValue { get; set; }
}