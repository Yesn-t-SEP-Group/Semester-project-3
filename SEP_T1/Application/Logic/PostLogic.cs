using Application.DaoInterfaces;
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

        ValidePost(dto);
        PostReadDto created = await _postDao.CreateAsync(dto);
        return created;
    }

    public Task<IEnumerable<PostReadDto>> GetAsync(SearchPostParametersDto searchParameters)
    {
        return _postDao.GetAsync(searchParameters);
    }


    public Task<IEnumerable<PostReadDto>> GetAsync()
    {
        return _postDao.GetAsync();
    }


    private void ValidePost(PostCreationDto dto)
    {
        if (string.IsNullOrEmpty(dto.Title)) throw new Exception("Title cannot be empty.");
        if (string.IsNullOrEmpty(dto.description)) throw new Exception("Body cannot be empty.");
        // other validation stuff
    }

    public  async Task UpdateAsync(PostUpdateDto dto)
    {
        PostReadDto? existing = await _postDao.GetByIdAsync(dto.Id);

        if (existing == null)
        {
            throw new Exception($"Post with ID {dto.Id} not found!");
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

        /*
        int userInt =  existing.OwnerId;
        string titletoUse = dto.Title ?? existing.Title;
        string bodyToUse = dto.description ?? existing.description;
        DateTime creationDate = DateTime.Now;
        string locationToUse = dto.location ?? existing.location;
        int categories = dto.categories;
        string picture = dto.picture ?? existing.picture;
        double price = dto.price;


        Post updated = new()
        {
            OwnerId = userInt,
            Title = titletoUse,
            description = bodyToUse,
            Id = existing.Id,
            categories = categories,
            creationDate = creationDate,
            location = locationToUse,
            picture = picture,
            price = price

        };

        ValidatePost(updated);

        PostUpdateDto updatedDto = new()
        {
            OwnerId = userInt,
            Title = titletoUse,
            description = bodyToUse,
            Id = existing.Id,
            categories = categories,
            location = locationToUse,
            picture = picture,
            price = price
        };
        */
        await _postDao.UpdateAsync(dto);
    }

    public  async Task DeleteAsync(int id)
    {
        PostReadDto? post = await _postDao.GetByIdAsync(id);
        if (post == null)
        {
            throw new Exception($"Post with ID {id} was not found!");
        }


        await _postDao.DeleteAsync(id);
    }

    public  async Task<PostReadDto> GetByIdAsync(int id)
    {
        PostReadDto? post = await _postDao.GetByIdAsync(id);
        if (post == null)
        {
            throw new Exception($"Post with id {id} not found");
        }

        return post;

    }

    public async Task<UserReadDto> GetPostOwner(int ownerId)
    {
       return  await _postDao.GetPostOwnerAsync(ownerId);
    }

    public async Task<CategoryReadDto> GetPostCategoryAsync(int postId)
    {
        return await _postDao.GetPostCategoryAsync(postId);
    }

    public async Task<CategoryReadDto> CreateCategoryAsync(string description)
    {
        return await _postDao.CreateCategoryAsync(description);
    }

    public async Task<IEnumerable<CategoryReadDto>> GetAllCategoriesAsync()
    {
        return await _postDao.GetAllCategoriesAsync();
    }

    public async Task DeleteCategoryAsync(int categoryId)
    {
        await _postDao.DeleteCategoryAsync(categoryId);
    }

    private  void ValidatePost(Post dto)
    {
        
        if (string.IsNullOrEmpty(dto.Title)) throw new Exception("Title cannot be empty.");
        if (string.IsNullOrEmpty(dto.description)) throw new Exception("Body cannot be empty.");
        // other validation stuff
    }
}
