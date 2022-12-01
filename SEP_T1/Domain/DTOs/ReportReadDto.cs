namespace Domain.DTOs;

public class ReportReadDto
{
    public int ReportId { get; set; }
    public int ReportedUserId { get; set; }
    public DateTime ReportDate { get; set; }
    public string Reason { get; set; } = null!;
}