using Domain.DTOs;
using Domain.Models;

namespace HttpClients.ClientInterfaces;

public interface IPostService
{
    Task CreateAsync(PostCreationDto dto);
    Task<ICollection<PostReadDto>> GetAsync(
        int? category,
        string? titleContains
        //,string? bodyContains
    );
    Task<PostReadDto> GetByIdAsync(int id);
    Task UpdateAsync(PostUpdateDto dto);
    Task DeleteAsync(int id);
    
    Task<CategoryReadDto> GetPostCategoryAsync(int postId);
    Task<CategoryReadDto> CreateCategoryAsync(string description);
    Task<IEnumerable<CategoryReadDto>> GetAllCategoriesAsync();
    Task DeleteCategoryAsync(int categoryId);

}