namespace Domain.DTOs;

public class MessageCreationDto
{
    public int UserFromId { get; set; }
    public int UserToId { get; set; }
    public string MessageText { get; set; }
}