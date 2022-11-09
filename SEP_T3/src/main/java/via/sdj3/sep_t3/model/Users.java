package via.sdj3.sep_t3.model;


import via.sdj3.sep_t3.protobuf.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

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

    /**
     * Sets all the variables from a grpc user
     * @param userFromGrpc protobuf.User
     */
    public void convertFromGrpc(User userFromGrpc)
    {
        id=userFromGrpc.getId();
        username=userFromGrpc.getUsername();
        userPass=userFromGrpc.getUserPass();
        fullName=userFromGrpc.getFullName();
        email=userFromGrpc.getEmail();
        phoneNumber=userFromGrpc.getPhoneNumber();
        address=userFromGrpc.getAddress();
        registeredOn = new Timestamp(userFromGrpc.getRegisteredOn()).toLocalDateTime().toLocalDate();
        lastSeen = new Timestamp(userFromGrpc.getLastSeen()).toLocalDateTime().toLocalDate();
    }

    /**
     * Use this to convert to the Grpc class
     * @return protobuf.User
     */
    public User convertToGrpc()
    {
        User user= User.newBuilder()
                .setId(id)
                .setUsername(username)
                .setUserPass(userPass)
                .setFullName(fullName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setAddress(address)
                .setRegisteredOn((int) registeredOn.toEpochSecond(LocalTime.NOON, ZoneOffset.MIN))
                .setLastSeen((int) registeredOn.toEpochSecond(LocalTime.NOON, ZoneOffset.MIN))
                .build();
        return user;
    }
}
