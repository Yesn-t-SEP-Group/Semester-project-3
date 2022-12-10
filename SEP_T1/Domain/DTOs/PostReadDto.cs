using Domain.Models.enums;

namespace Domain.Models;

public class PostReadDto
{
    public int Id { get; set; }

    public int OwnerId { get; set; }

    public string Title { get; set; }

    public string description { get; set; }

    public DateTime creationDate { get; set; }

    public string location { get; set; }

    public int categories { get; set; }
    

    public String picture { get; set; }

    public double price { get; set; }
    
    public string? PosterUsername { get; set; }
    public string? categoryString { get; set; }
    public StatusEnum Status { get; set; }

    public PostReadDto()
    {
    }
}