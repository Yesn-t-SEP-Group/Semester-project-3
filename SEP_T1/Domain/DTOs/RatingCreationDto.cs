namespace Domain.DTOs;

public class RatingCreationDto
{
    public int UserFromId { get; set; }
    public int UserToId { get; set; }
    public int RatingValue { get; set; }
}