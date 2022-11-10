package via.sdj3.sep_t3.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name ="posts")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Posts
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
    private Categories category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "sellerid", nullable = false)
    @ToString.Exclude
    private Users sellerid;

    @Column(name = "picture_url", length = 50)
    private String pictureUrl;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Posts posts = (Posts) o;
        return id != null && Objects.equals(id, posts.id);
    }
}
