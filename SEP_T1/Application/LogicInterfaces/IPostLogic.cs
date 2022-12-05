using Domain.DTOs;
using Domain.Models;

namespace Application.LogicInterfaces;

public interface IPostLogic
{
    Task<PostReadDto> CreateAsync(PostCreationDto dto);
    Task<IEnumerable<PostReadDto>> GetAsync();
    Task UpdateAsync(PostUpdateDto dto);
    Task DeleteAsync(int id);

    Task<PostReadDto> GetByIdAsync(int id);
    Task<UserReadDto> GetPostOwner(int userId);
    Task<CategoryReadDto> GetPostCategoryAsync(int postId);
    Task<CategoryReadDto> CreateCategoryAsync(string description);
    Task<IEnumerable<CategoryReadDto>> GetAllCategoriesAsync();
    
}