using Domain.Models;

namespace Domain.DTOs;

public class PostDetailDto
{
    public int Id { get; set; }

    public string Title { get; set; }

    public UserReadDto Owner { get; set; }
    public string description { get; set; }

    public DateTime creationDate { get; set; }

    public string location { get; set; }

    public string categories { get; set; }
    

    public String picture { get; set; }

    public double price { get; set; }

    public PostDetailDto(int id)
    {
        Id = id;
    }
    
    public PostDetailDto()
    {
    }
}