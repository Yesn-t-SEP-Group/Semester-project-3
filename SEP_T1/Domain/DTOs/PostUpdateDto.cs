namespace Domain.Models;

public class PostUpdateDto
{
    public int Id { get; set; }

    public int OwnerId { get; set; }

    public string Title { get; set; }

    public string description { get; set; }
    
    public string location { get; set; }

    public int categories { get; set; }
    

    public String picture { get; set; }

    public double price { get; set; }

    // public bool IsCompleted { get; set; }

    public PostUpdateDto(int id)
    {
        Id = id;
    }

    public PostUpdateDto()
    {
    }
}