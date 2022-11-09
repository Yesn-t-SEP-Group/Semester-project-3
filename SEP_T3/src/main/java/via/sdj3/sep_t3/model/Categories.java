package via.sdj3.sep_t3.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "categories")
public class Categories
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Integer id;

    @Column(name = "description", length = 50)
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Posts> posts = new LinkedHashSet<>();

    public Set<Posts> getPosts()
    {
        return posts;
    }

    public void setPosts(Set<Posts> posts)
    {
        this.posts = posts;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
}
