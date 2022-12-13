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
            CreateMap<PostUpdateDto, PostReadGrpcDto>().ReverseMap();
            CreateMap<UserUpdateDto, UserUpdateGrpcDTO>().ReverseMap();
            CreateMap<PostCreationDto, PostCreationGrpcDto>().ReverseMap();
            CreateMap<PostReadDto, PostReadGrpcDto>().ReverseMap().ForMember(x => x.creationDate,
                y => y.MapFrom(src =>DateTime.UnixEpoch.AddSeconds(src.CreationDate)));
            CreateMap<UserReadGrpcDTO, UserReadDto>()
                .ForMember(x => x.LastSeenDateTime, y => y.MapFrom(src => DateTime.UnixEpoch.AddSeconds(src.LastSeenDate)))
                .ForMember(x => x.RegistrationDateTime, y => y.MapFrom(src => DateTime.UnixEpoch.AddSeconds(src.RegistrationDate)));


            CreateMap<PostCreationDto, PostGrpcDao>();
            CreateMap<PostCreationDto, PostCreationGrpcDto>().ReverseMap();
            CreateMap<PostReadGrpcDto, Post>();

            CreateMap<ReportReadDto, ReportReadGrpcDto>().ReverseMap().ForMember(x => x.ReportDate,
                y => y.MapFrom(src => DateTime.UnixEpoch.AddSeconds(src.ReportDate)));

            CreateMap<ReportCreationDto, ReportCreationGrpcDto>();


            CreateMap<RatingCreationDto, RatingCreationGrpcDto>();
            CreateMap<RatingReadDto, RatingReadGrpcDto>().ReverseMap();
            CreateMap<CategoryReadDto, CategoryReadGrpcDto>().ReverseMap();
            CreateMap<MessageCreationDto, CreateDMGrpcDto>();
            CreateMap<MessageReadDto, ReadDMGrpcDto>().ReverseMap();
        }
    }
}
