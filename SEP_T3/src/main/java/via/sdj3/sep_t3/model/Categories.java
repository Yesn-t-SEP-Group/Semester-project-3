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
public class Categories
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Integer id;

    @Column(name = "description", length = 50)
    private String description;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private Set<Posts> posts = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Categories that = (Categories) o;
        return id != null && Objects.equals(id, that.id);
    }
}
