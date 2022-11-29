package via.sdj3.sep_t3.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import via.sdj3.sep_t3.protobuf.PostReadGrpcDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Entity(name ="posts")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
/**
 * is a class for the posts
 */
public class Post
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Integer id;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "description")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Column(name = "location", length = 100)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "category_id", nullable = false)
    @ToString.Exclude
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "sellerid", nullable = false)
    @ToString.Exclude
    private User sellerid;

    @Column(name = "picture_url", length = 50)
    private String pictureUrl;

    @Column(name = "price", nullable = false)
    private Integer price;

    /**
     * converts the data for usage in the proto file
     * @return returns the data that used by the proto file
     */
    public PostReadGrpcDto convertToPostReadGrpcDto()
    {
        return PostReadGrpcDto.newBuilder()
                .setId(id)
                .setCreationDate((int) creationDate.toEpochSecond(LocalTime.NOON, ZoneOffset.MIN))
                .setDescription(description)
                .setLocation(location)
                .setCategories(category.getId())
                .setOwnerId(sellerid.getId())
                .setPicture(pictureUrl)
                .setPrice(price)
                .build();
    }

    /**
     * it compares 2 objects
     * @param o takes in parameter 0 to be used in the comparation
     * @return answers with true or false after the comparation
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Post post = (Post) o;
        return id != null && Objects.equals(id, post.id);
    }
}
