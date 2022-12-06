namespace Domain.DTOs;

public class MessageReadDto
{
    public int DmId { get; set; }
    public int UserFromId { get; set; }
    public int UserToId { get; set; }
    public string MessageText { get; set; }
    public string? UserFromName { get; set; }

}