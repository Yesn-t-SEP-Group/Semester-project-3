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
 * Fields that cant be automated has to be done manually.
 */
@Mapper
public interface MapperImplementation
{
    /**
     * The constant INSTANCE.
     */
    MapperImplementation INSTANCE= Mappers.getMapper(MapperImplementation.class);

    /**
     * Convert from create rating grpc dto rating.
     *
     * @param ratingCreationGrpcDto the rating creation grpc dto
     * @return the rating
     */
    Rating convertFromCreateRatingGrpcDto(RatingCreationGrpcDto ratingCreationGrpcDto);

    /**
     * Use this to convert from grpc to model
     *
     * @param userCreationGrpcDto request from grpc
     * @return JPA compatible user
     * @implNote Last seen and Registered on has to be set manually
     */
    @Mapping(source = "name", target = "fullName")
    @Mapping(source = "password", target = "userPass")
    User convertFromCreateUserGrpcDto(UserCreationGrpcDto userCreationGrpcDto);

    /**
     * Convert from create posts grpc dto post.
     *
     * @param postCreationGrpcDto the post creation grpc dto
     * @return the post
     */
    @Mapping(source = "picture",target = "pictureUrl")
    Post convertFromCreatePostsGrpcDto(PostCreationGrpcDto postCreationGrpcDto);
}
