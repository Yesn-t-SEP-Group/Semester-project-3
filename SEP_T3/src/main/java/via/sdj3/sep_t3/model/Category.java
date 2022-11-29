package via.sdj3.sep_t3.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "categories")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
/**
 * the categories contained in the marketplace
 */
public class Category
{
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
}
