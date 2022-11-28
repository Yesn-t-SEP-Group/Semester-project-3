using Application.DaoInterfaces;
using AutoMapper;
using Domain.DTOs;
using Domain.Models;
using GrpcData.DI;

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
    
    public async Task<Post> CreateAsync(Post post)
    {
        var convertedToGrpc = this._mapper.Map<PostCreationDto>(post);

        var client = _grpcService.CreatePostServiceClient();
        throw new NotImplementedException();
    }

    public Task DeleteAsync(int id)
    {
        throw new NotImplementedException();
    }

    public Task<IEnumerable<Post>> GetAsync(SearchPostParametersDto searchParameters)
    {
        throw new NotImplementedException();
    }

    public async Task<Post?> GetByIdAsync(int postId)
    {
        var client = _grpcService.CreatePostServiceClient();
        var result = await client.getPostByIdAsync(new GenericMessage{Message = postId.ToString()});
        return _mapper.Map<Post>(result);
    }

    public Task UpdateAsync(Post dto)
    {
        throw new NotImplementedException();
    }
}