package via.sdj3.sep_t3.adapters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import via.sdj3.sep_t3.model.Post;
import via.sdj3.sep_t3.model.Rating;
import via.sdj3.sep_t3.model.User;
import via.sdj3.sep_t3.protobuf.PostCreationGrpcDto;
import via.sdj3.sep_t3.protobuf.RatingCreationGrpcDto;
import via.sdj3.sep_t3.protobuf.UserCreationGrpcDto;
/**
 * Fields that cant be automated ahs to be done manually.
 */
@Mapper
public interface MapperImplementation
{
    MapperImplementation INSTANCE= Mappers.getMapper(MapperImplementation.class);

    Rating convertFromCreateRatingGrpcDto(RatingCreationGrpcDto ratingCreationGrpcDto);

    /**
     * Use this to convert from grpc to model
     * @implNote Last seen and Registered on has to be set manually
     * @param userCreationGrpcDto request from grpc
     * @return JPA compatible user
     */
    @Mapping(source = "name", target = "fullName")
    @Mapping(source = "password", target = "userPass")
    User convertFromCreateUserGrpcDto(UserCreationGrpcDto userCreationGrpcDto);

    @Mapping(source = "picture",target = "pictureUrl")
    Post convertFromCreatePostsGrpcDto(PostCreationGrpcDto postCreationGrpcDto);
}
