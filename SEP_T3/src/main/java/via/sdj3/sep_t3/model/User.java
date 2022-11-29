package via.sdj3.sep_t3.model;


import lombok.*;
import org.hibernate.Hibernate;
import via.sdj3.sep_t3.protobuf.UserReadGrpcDTO;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Entity(name = "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class User
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
    private LocalDateTime registeredOn;

    @Column(name = "last_seen")
    private LocalDateTime lastSeen;

    @Column(name = "role", length = 20)
    private String role;

    /**
     * Sets all the variables from a grpc user
     * @param userFromGrpc protobuf.User
     * @deprecated
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
    }




    /**
     * It will convert all the user objects into data that the gRPC dto can use
     * @return it returns the data into the gRPC DTO so it matches the java part
     */
    public UserReadGrpcDTO convertToUserReadGrpcDto()
    {
        return UserReadGrpcDTO.newBuilder()
                .setId(id)
                .setUsername(username)
                .setName(fullName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setAddress(address)
                .setRole(role)
                .setRegistrationDate((int) registeredOn.toEpochSecond(ZoneOffset.UTC))
                .setLastSeenDate((int) lastSeen.toEpochSecond(ZoneOffset.UTC))
                .build();
    }

    /**
     * It will check if the id and user id are matching
     * @param o takes the user id
     * @return it will return true after it checks if the Ids match
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

}
