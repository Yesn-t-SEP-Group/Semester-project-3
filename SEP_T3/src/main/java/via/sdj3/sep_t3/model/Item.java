package via.sdj3.sep_t3.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name="item")
public class Item
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemid", nullable = false)
    private Integer id;

    @Column(name = "price", nullable = false, precision = 9, scale = 2)
    private BigDecimal price;

    @Column(name = "picture", length = 100)
    private String picture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postid")
    private Post postid;

    public Post getPostid()
    {
        return postid;
    }

    public void setPostid(Post postid)
    {
        this.postid = postid;
    }

    public String getPicture()
    {
        return picture;
    }

    public void setPicture(String picture)
    {
        this.picture = picture;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
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
