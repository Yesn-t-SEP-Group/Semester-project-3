using AutoMapper;
using Domain.DTOs;
using Domain.Models;
using GrpcData.DAOs;

namespace WebAPI
{
    public class MapperConfiguration : Profile
    {
        public MapperConfiguration()
        {
            // compatibility for fileDTO
            CreateMap<User, UserReadDto>().ReverseMap();
            CreateMap<UserCreationDto, User>();

            CreateMap<UserCreationGrpcDto, UserCreationDto>();
            CreateMap<UserCreationDto, UserCreationGrpcDto>();
            CreateMap<PostCreationDto, PostCreationGrpcDto>().ReverseMap();
            CreateMap<PostReadDto, PostReadGrpcDto>().ReverseMap();
            CreateMap<UserReadGrpcDTO, UserReadDto>()
                .ForMember(x => x.LastSeenDateTime, y => y.MapFrom(src => DateTime.UnixEpoch.AddSeconds(src.LastSeenDate)))
                .ForMember(x => x.RegistrationDateTime, y => y.MapFrom(src => DateTime.UnixEpoch.AddSeconds(src.RegistrationDate)));


            CreateMap<PostCreationDto, PostGrpcDao>();
            CreateMap<PostCreationDto, PostCreationGrpcDto>().ReverseMap();
            CreateMap<PostReadGrpcDto, Post>();
        }
    }
}
