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

    public Task<IEnumerable<Post>> GetAsync(SearchPostParametersDto searchParameters)
    {
        throw new NotImplementedException();
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
}