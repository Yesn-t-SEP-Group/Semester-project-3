using System.IO.MemoryMappedFiles;
using System.Runtime.InteropServices.Marshalling;
using System.Xml.Linq;
using Application.DaoInterfaces;
using AutoMapper;
using Domain.DTOs;
using Domain.Models;
using GrpcData.DI;
using Serilog;

namespace GrpcData.DAOs;

public class PostGrpcDao : IPostDao
{
    private readonly IGrpcService _grpcService;
    private readonly IMapper _mapper;

    public PostGrpcDao(
        IGrpcService grpcService,
        IMapper mapper)
    {
        this._grpcService = grpcService;
        _mapper = mapper;
    }
    
    public async Task<PostReadDto> CreateAsync(PostCreationDto post)
    {

        var client = this._grpcService.CreatePostServiceClient();
        
            var convertedToGrpc = this._mapper.Map<PostCreationGrpcDto>(post);
            var result= await client.createPostAsync(convertedToGrpc);
            var mapped = _mapper.Map<PostReadDto>(result);
            return mapped;
    }

    public async Task<IEnumerable<PostReadDto>> GetAsync(SearchPostParametersDto searchParameters)
    {
        var client = _grpcService.CreatePostServiceClient();
        var result = await client.getAllPostsAsync(new Empty());
        
        List<PostReadDto> list = new List<PostReadDto>();
        foreach (var read in result.Post)
        {
            var temp = _mapper.Map<PostReadDto>(read);
            list.Add(temp);
        }

        if (searchParameters.category != 0 && searchParameters.category <=5)
        {
           list = (List<PostReadDto>)list.Where((post =>post.categories == searchParameters.category));
        }
        
        if (!string.IsNullOrEmpty(searchParameters.TitleContains))
        {
           list = (List<PostReadDto>)list.Where(t =>
               t.Title.Contains(searchParameters.TitleContains, StringComparison.OrdinalIgnoreCase));
        }
        return list.AsEnumerable();
    }

    public async Task DeleteAsync(int id)
    {
        var client = _grpcService.CreatePostServiceClient();
        var result =  await client.deletePostAsync(new GenericMessage{Message = id.ToString()});
        if (!result.Message.Equals("true"))
        {
            Log.Logger.Error(result.Message);
            throw new ArgumentException(result.Message);
        }

    }
    public async Task<UserReadDto> GetPostOwnerAsync(int userId)
    { 
        //we query up users
        var userServiceClient = _grpcService.CreateUserServiceClient();

        var result = await userServiceClient.getUserByIdAsync(new GenericMessage { Message = userId.ToString() });
        var mapped = _mapper.Map<UserReadDto>(result);
        return mapped;
    }

    public async Task<IEnumerable<PostReadDto>> GetAsync()
    {
        var client = _grpcService.CreatePostServiceClient();
        var result =  await client.getAllPostsAsync(new Empty());
        List<PostReadDto> list = new List<PostReadDto>();
        foreach (var read in result.Post)
        {
            var temp = _mapper.Map<PostReadDto>(read);
            list.Add(temp);
        }
        return list;

    }

    public async Task<PostReadDto?> GetByIdAsync(int postId)
    {
        var client = _grpcService.CreatePostServiceClient();
        var result = await client.getPostByIdAsync(new GenericMessage{Message = postId.ToString()});
        return _mapper.Map<PostReadDto>(result);
        
    }

    public async Task UpdateAsync(PostUpdateDto dto)
    {
        var client = _grpcService.CreatePostServiceClient();
        var mapped = _mapper.Map<PostReadGrpcDto>(dto);
        var result = await client.updatePostAsync(mapped);
        
    }

    public async Task<CategoryReadDto> GetPostCategoryAsync(int postId)
    {
        var client = _grpcService.CreatePostServiceClient();
        var result = await client.getCategoryByPostIdAsync(new GenericMessage { Message = postId.ToString() });
        return _mapper.Map<CategoryReadDto>(result);
    }

    public async Task<CategoryReadDto> CreateCategoryAsync(string description)
    {
        var client = _grpcService.CreatePostServiceClient();
        var result = await client.createCategoryAsync(new CategoryCreationGrpcDto { Description = description });
        return _mapper.Map<CategoryReadDto>(result);
    }

    public async Task<IEnumerable<CategoryReadDto>> GetAllCategoriesAsync()
    {
        var client = _grpcService.CreatePostServiceClient();
        var result = await client.getAllCategoriesAsync(new Empty());
        var list = new List<CategoryReadDto>();
        foreach (var categoryReadGrpcDto in result.Categories)
        {
            list.Add(_mapper.Map<CategoryReadDto>(categoryReadGrpcDto));
        }

        return list;
    }

    public async Task DeleteCategoryAsync(int categoryId)
    {
        var client = _grpcService.CreatePostServiceClient();
        await client.deleteCategoryAsync(new GenericMessage{Message = categoryId.ToString()});
    }
}