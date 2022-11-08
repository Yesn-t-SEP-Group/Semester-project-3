package via.sdj3.sep_t3.model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "users")
public class Users
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Column(name = "user_pass", nullable = false, length = 32)
    private String userPass;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 11)
    private String phoneNumber;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "registered_on")
    private LocalDate registeredOn;

    @Column(name = "last_seen")
    private LocalDate lastSeen;

    public LocalDate getLastSeen()
    {
        return lastSeen;
    }

    public void setLastSeen(LocalDate lastSeen)
    {
        this.lastSeen = lastSeen;
    }

    public LocalDate getRegisteredOn()
    {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn)
    {
        this.registeredOn = registeredOn;
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

    public String getUserPass()
    {
        return userPass;
    }

    public void setUserPass(String userPass)
    {
        this.userPass = userPass;
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
}
