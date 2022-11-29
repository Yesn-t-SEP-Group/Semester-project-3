namespace Domain.DTOs;

public class PostCreationDto
{
    public PostCreationDto(int ownerId, string title, string description, DateTime creationDate, string location, int categories,
        string picture, double price)
    {
        OwnerId = ownerId;
        Title = title ?? throw new ArgumentNullException(nameof(title));
        this.description = description ?? throw new ArgumentNullException(nameof(description));
        this.creationDate = creationDate;
        this.location = location ?? throw new ArgumentNullException(nameof(location));
        this.categories = categories;
        this.picture = picture ?? throw new ArgumentNullException(nameof(picture));
        this.price = price;
    }
    

    public int OwnerId { get; set; }

    public string Title { get; set; }

    public string description { get; set; }

    public DateTime creationDate { get; set; }

    public string location { get; set; }

    public int categories { get; set; }


    public String picture { get; set; }

    public double price { get; set; }

    // public bool IsCompleted { get; set; }

    public PostCreationDto()
    {
    }
}

/*
    public override string ToString()
    {
        return "id: " + OwnerId + "/title: " + Title + "/body: " + Body;
    }
    */