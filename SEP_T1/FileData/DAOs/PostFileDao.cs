using Application.DaoInterfaces;
using AutoMapper;
using Domain.DTOs;
using Domain.Models;

namespace FileData.DAOs;

public class PostFileDao :IPostDao
{
    private readonly FileContext context;
    private readonly IMapper _mapper;
    private IPostDao _postDaoImplementation;

    public PostFileDao(FileContext context)
    {
        this.context = context;
    }

    public Task<Post> CreateAsync(Post post)
    {
        int id = 1;
        if (context.Posts.Any())
        {
            id = context.Posts.Max(t => t.Id);
            id++;
        }

        post.Id = id;

        context.Posts.Add(post);
        context.SaveChanges();

        return Task.FromResult(post);
    }

    public Task<PostReadDto> CreateAsync(PostCreationDto post)
    {
        int id = 1;
        if (context.Posts.Any())
        {
            id = context.Posts.Max(t => t.Id);
            id++;
        }

        var result = _mapper.Map<Post>(post);
        
        result.Id = id;
        
        var postRead = _mapper.Map<PostReadDto>(result);

       

        context.Posts.Add(result);
        context.SaveChanges();
        
        return Task.FromResult(postRead);
    }

    
    public Task<IEnumerable<PostReadDto>> GetAsync(SearchPostParametersDto searchParams)
    { /*
        IEnumerable<Post> result = context.Todos.AsEnumerable();
 /*
        if (!string.IsNullOrEmpty(searchParams.Username))
        {
            // we know username is unique, so just fetch the first
            // todo
            //result = context.Todos.Where(todo =>
            //    todo.Owner.UserName.Equals(searchParams.Username, StringComparison.OrdinalIgnoreCase));
        }
        */
/*
        if (searchParams.UserId != null)
        {
            result = result.Where(t => t.OwnerId == searchParams.UserId);
        }
        */

        /*if (searchParams.CompletedStatus != null)
        {
            result = result.Where(t => t.IsCompleted == searchParams.CompletedStatus);
        }*/
/*
        if (!string.IsNullOrEmpty(searchParams.TitleContains))
        {
            result = result.Where(t =>
                t.Title.Contains(searchParams.TitleContains, StringComparison.OrdinalIgnoreCase));
        }
        
        /*if (!string.IsNullOrEmpty(searchParams.BodyContains))
        {
            result = result.Where(t =>
                t.Title.Contains(searchParams.BodyContains, StringComparison.OrdinalIgnoreCase));
        }*/
    
       // return Task.FromResult(result);
       throw new NotImplementedException();
    }

    public Task<IEnumerable<PostReadDto>> GetAsync()
    {
        throw new NotImplementedException();
    }

    Task<PostReadDto?> IPostDao.GetByIdAsync(int postId)
    {
        throw new NotImplementedException();
    }

    public Task UpdateAsync(PostUpdateDto dto)
    {
        throw new NotImplementedException();
    }

    public Task<Post?> GetByIdAsync(int postId)
    {
        Post? existing = context.Posts.FirstOrDefault(t => t.Id == postId);
        return Task.FromResult(existing);
    }

    public Task UpdateAsync(Post dto)
    {
        Post? existing = context.Posts.FirstOrDefault(post => post.Id == dto.Id);
        if (existing == null)
        {
            throw new Exception($"Todo with id {dto.Id} does not exist!");
        }

        context.Posts.Remove(existing);
        context.Posts.Add(dto);
        
        context.SaveChanges();
        
        return Task.CompletedTask;
    }

    public Task DeleteAsync(int id)
    {
        Post? existing = context.Posts.FirstOrDefault(todo => todo.Id == id);
        if (existing == null)
        {
            throw new Exception($"Post with id {id} does not exist!");
        }

        context.Posts.Remove(existing); 
        context.SaveChanges();

        return Task.CompletedTask;
    }

    public Task<UserReadDto> GetPostOwnerAsync(int dto)
    {
        throw new NotImplementedException();
    }

    public Task<CategoryReadDto> GetPostCategoryAsync(int postId)
    {
        throw new NotImplementedException();
    }

    public Task<CategoryReadDto> CreateCategoryAsync(string description)
    {
        throw new NotImplementedException();
    }

    public Task<IEnumerable<CategoryReadDto>> GetAllCategoriesAsync()
    {
        throw new NotImplementedException();
    }

    public Task DeleteCategoryAsync(int categoryId)
    {
        throw new NotImplementedException();
    }
}