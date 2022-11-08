package via.sdj3.sep_t3.backendModel;

import java.time.LocalDate;
import java.util.Date;

public class BackendUser
{
        private int id;
        private String username;
        private String password;
        private String fullName;

        private String email;
        private String phoneNo;
        private String address;
        private int rating;

        private LocalDate registeredOn;

        private LocalDate lastSeen;


    public BackendUser(int id, String username, String password, String fullName, String email, String phoneNo, String address, int rating, LocalDate registeredOn, LocalDate lastSeen)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.rating = 3;
        this.registeredOn = registeredOn;
        this.lastSeen = lastSeen;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhoneNo()
    {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo)
    {
        this.phoneNo = phoneNo;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public LocalDate getRegisteredOn()
    {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn)
    {
        this.registeredOn = registeredOn;
    }

    public LocalDate getLastSeen()
    {
        return lastSeen;
    }

    public void setLastSeen(LocalDate lastSeen)
    {
        this.lastSeen = lastSeen;
    }
}
