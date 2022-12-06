package via.sdj3.sep_t3.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import via.sdj3.sep_t3.protobuf.RatingReadGrpcDto;

import javax.persistence.*;
import java.util.Objects;

@Entity(name="ratings")
@Getter
@Setter
@ToString
@RequiredArgsConstructor

/***
 * the rating class used to write values into the database
 */
public class Rating
{
    /***
     * the method fields will write new values if their ids match using the equals method at the end.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id", nullable = false)
    private Integer id;

    @Column(name = "rating_value", nullable = false)
    private Integer ratingValue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_from", nullable = false)
    @ToString.Exclude
    private User userFrom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_to", nullable = false)
    @ToString.Exclude
    private User userTo;

    /**
     * It will compare if the id of the user and the rating is matching
     * @param o it will take the ratings as a parameter
     * @return it will compare and return the result if the ids are matching
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rating rating = (Rating) o;
        return id != null && Objects.equals(id, rating.id);
    }

    /**
     * It will convert the rating into what gRPC dto uses
     * @return it returns a written value into the dto that matches the java part
     */
    public RatingReadGrpcDto convertToRatingReadGrpcDto()
    {
        return RatingReadGrpcDto.newBuilder()
                .setRatingId(id)
                .setRatingValue(ratingValue)
                .setUserFromId(userFrom.getId())
                .setUserToId(userTo.getId())
                .build();
    }
}
