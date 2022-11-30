﻿using Domain.DTOs;
using Domain.Models;

namespace Application.DaoInterfaces;

public interface IPostDao
{
    //todo fix
    Task<PostReadDto> CreateAsync(PostCreationDto post);
    Task<IEnumerable<Post>> GetAsync(SearchPostParametersDto searchParameters);
  
  Task<IEnumerable<PostReadDto>> GetAsync();
    Task<PostReadDto?> GetByIdAsync(int postId);
    Task UpdateAsync(PostUpdateDto dto);
    Task DeleteAsync(int id);
    
    Task<UserReadDto> GetPostOwnerAsync(PostReadDto dto);

}