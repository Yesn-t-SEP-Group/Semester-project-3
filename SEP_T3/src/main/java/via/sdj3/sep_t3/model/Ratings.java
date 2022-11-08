package via.sdj3.sep_t3.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name="ratings")

public class Ratings
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id", nullable = false)
    private Integer id;

    @Column(name = "rating_value", nullable = false)
    private Integer ratingValue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_from", nullable = false)
    private Users usersFrom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_to", nullable = false)
    private Users usersTo;

    public Users getUserTo()
    {
        return usersTo;
    }

    public void setUserTo(Users usersTo)
    {
        this.usersTo = usersTo;
    }

    public Users getUserFrom()
    {
        return usersFrom;
    }

    public void setUserFrom(Users usersFrom)
    {
        this.usersFrom = usersFrom;
    }

    public Integer getRatingValue()
    {
        return ratingValue;
    }

    public void setRatingValue(Integer ratingValue)
    {
        this.ratingValue = ratingValue;
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
