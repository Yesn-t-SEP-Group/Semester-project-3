package via.sdj3.sep_t3.model;

import via.sdj3.sep_t3.backendModel.BackendUser;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Column(name = "userpass", nullable = false, length = 32)
    private String userpass;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 16)
    private String phoneNumber;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "rating", nullable = false, precision = 3, scale = 2)
    private BigDecimal rating;

    @OneToMany(mappedBy = "sellerid")
    private Set<Post> posts = new LinkedHashSet<>();

    public Set<Post> getPosts()
    {
        return posts;
    }

    public void setPosts(Set<Post> posts)
    {
        this.posts = posts;
    }

    public BigDecimal getRating()
    {
        return rating;
    }

    public void setRating(BigDecimal rating)
    {
        this.rating = rating;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getUserpass()
    {
        return userpass;
    }

    public void setUserpass(String userpass)
    {
        this.userpass = userpass;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * Convert from backend user.
     *
     * @param user the user
     */
    public void convertFromBackendUser(BackendUser user)
    {
        this.username=user.getUsername();
        this.userpass=user.getPassword();
        this.fullName=user.getFullName();
        this.email=user.getEmail();
        this.phoneNumber=user.getPhoneNo();
        this.address=user.getAddress();
        this.rating= BigDecimal.valueOf(user.getRating());
    }
    public BackendUser convertToBackendUser()
    {
        return new BackendUser(id,username,userpass,fullName,phoneNumber,address);
    }

}
