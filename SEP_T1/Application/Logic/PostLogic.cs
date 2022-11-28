﻿using Application.DaoInterfaces;
using Application.LogicInterfaces;
using Domain.DTOs;
using Domain.Models;

namespace Application.Logic;

public class PostLogic : IPostLogic
{
    private  readonly IPostDao _postDao;
    private  readonly IUserDao userDao;

    public PostLogic(IPostDao postDao, IUserDao userDao)
    {
        this._postDao = postDao;
        this.userDao = userDao;
    }
    
    public async Task<PostReadDto> CreateAsync(PostCreationDto dto)
    {
        UserReadDto? user = await userDao.GetByIdAsync(dto.OwnerId);
        if (user == null)
        {
            throw new Exception($"User with id {dto.OwnerId} was not found.");
        }

        ValidateTodo(dto);

        PostCreationDto post = new PostCreationDto(user.Id, dto.Body, dto.Title);
        PostReadDto created = await _postDao.CreateAsync(post);
        return created;
    }

    public Task<IEnumerable<Post>> GetAsync(SearchPostParametersDto searchParameters)
    {
        return _postDao.GetAsync(searchParameters);
    }

    private void ValidateTodo(PostCreationDto dto)
    {
        if (string.IsNullOrEmpty(dto.Title)) throw new Exception("Title cannot be empty.");
        if (string.IsNullOrEmpty(dto.Body)) throw new Exception("Body cannot be empty.");
        // other validation stuff
    }

    public  async Task UpdateAsync(PostUpdateDto dto)
    {
        PostReadDto? existing = await _postDao.GetByIdAsync(dto.Id);

        if (existing == null)
        {
            throw new Exception($"Todo with ID {dto.Id} not found!");
        }

        UserReadDto? user = null;
        if (dto.OwnerId != null)
        {
            user = await userDao.GetByIdAsync((int)dto.OwnerId);
            if (user == null)
            {
                throw new Exception($"User with id {dto.OwnerId} was not found.");
            }
        }


        int userInt =  existing.OwnerId;
        string titletoUse = dto.Title ?? existing.Title;
        string bodyToUse = dto.Body ?? existing.Body;
        
        Post updated = new()
        {
            OwnerId = userInt,
            Title = titletoUse,
            Body = bodyToUse,
            Id = existing.Id
        };

        ValidateTodo(updated);

        PostUpdateDto updatedDto = new()
        {
            OwnerId = userInt,
            Title = titletoUse,
            Body = bodyToUse,
            Id = existing.Id
        };

        /*
        User userToUse = user ?? existing.Owner;
        string titleToUse = dto.Title ?? existing.Title;
        string bodyToUse = dto.Body ?? existing.Body;
     //   bool completedToUse = dto.IsCompleted ?? existing.IsCompleted;
        
        Post updated = new (userToUse, titleToUse,bodyToUse)
        {
        //    IsCompleted = completedToUse,
            Id = existing.Id,
        };
        */
        
        
       // throw new ApplicationException("This needs to be updated...");
       // Post updated = new Post();
       // ValidateTodo(updated);

        await _postDao.UpdateAsync(updatedDto);
    }

    public  async Task DeleteAsync(int id)
    {
        PostReadDto? todo = await _postDao.GetByIdAsync(id);
        if (todo == null)
        {
            throw new Exception($"Todo with ID {id} was not found!");
        }
/*
        if (!todo.IsCompleted)
        {
            throw new Exception("Cannot delete un-completed Todo!");
        }
        */

        await _postDao.DeleteAsync(id);
    }

    public  async Task<PostBasicDto> GetByIdAsync(int id)
    {
        PostReadDto? todo = await _postDao.GetByIdAsync(id);
        if (todo == null)
        {
            throw new Exception($"Todo with id {id} not found");
        }

        return new PostBasicDto(todo.Id, todo.OwnerId.ToString(), todo.Title, todo.Body); //,todo.IsCompleted);
    }
    
    private  void ValidateTodo(Post dto)
    {
        
        if (string.IsNullOrEmpty(dto.Title)) throw new Exception("Title cannot be empty.");
        if (string.IsNullOrEmpty(dto.Body)) throw new Exception("Body cannot be empty.");
        // other validation stuff
    }
}
