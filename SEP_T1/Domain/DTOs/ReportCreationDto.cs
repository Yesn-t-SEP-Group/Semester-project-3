namespace Domain.DTOs;

public class ReportCreationDto
{
    public int ReportedUserId { get; set; }
    public string Reason { get; set; } = null!;
}