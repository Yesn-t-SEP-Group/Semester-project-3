using Domain.DTOs;
using Domain.Models;

namespace Application.DaoInterfaces;

public interface IPostDao
{
    //todo fix
    Task<PostReadDto> CreateAsync(PostCreationDto post);
    Task<IEnumerable<PostReadDto>> GetAsync(SearchPostParametersDto searchParameters);
  
  Task<IEnumerable<PostReadDto>> GetAsync();
    Task<PostReadDto?> GetByIdAsync(int postId);
    Task UpdateAsync(PostUpdateDto dto);
    Task DeleteAsync(int id);
    
    Task<UserReadDto> GetPostOwnerAsync(int dto);
    
    Task<CategoryReadDto> GetPostCategoryAsync(int postId);
    Task<CategoryReadDto> CreateCategoryAsync(string description);
    Task<IEnumerable<CategoryReadDto>> GetAllCategoriesAsync();
    Task DeleteCategoryAsync(int categoryId);

}