﻿namespace Domain.DTOs;

public class PostReadDto

{
    public int Id { get; set; }

    public int OwnerId { get; set; }

    public string Title { get; set; }

    public string Body { get; set; }

    // public bool IsCompleted { get; set; }
}
