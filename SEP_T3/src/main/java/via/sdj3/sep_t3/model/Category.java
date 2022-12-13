package via.sdj3.sep_t3.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import via.sdj3.sep_t3.protobuf.CategoryReadGrpcDto;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Category entity used by JPA
 */
@Entity(name = "categories")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Category
{
    /***
     * this method compares id, description and posts as objects to be considered equal if their id fields are non-null and equal.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Integer id;

    @Column(name = "description", length = 50)
    private String description;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private Set<Post> posts = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category that = (Category) o;
        return id != null && Objects.equals(id, that.id);
    }

    /***
     * The method converts category into a form for gRPC connection
     * @return awaits a received return
     */
    public CategoryReadGrpcDto convertToGrpcReadDto()
    {
        return CategoryReadGrpcDto.newBuilder()
                .setId(id)
                .setDescription(description)
                .build();
    }
}
