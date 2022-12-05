package via.sdj3.sep_t3;

import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import via.sdj3.sep_t3.model.Category;
import via.sdj3.sep_t3.model.Post;
import via.sdj3.sep_t3.model.Rating;
import via.sdj3.sep_t3.model.User;
import via.sdj3.sep_t3.repository.CategoriesRegistry;
import via.sdj3.sep_t3.repository.PostRegistry;
import via.sdj3.sep_t3.repository.RatingsRegistry;
import via.sdj3.sep_t3.repository.UserRegistry;
import static org.assertj.core.api.Assertions.*;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
        properties = {
                "spring.datasource.driver-class-name=org.h2.Driver",
                //"spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;INIT=CREATE SCHEMA IF NOT EXISTS sep3;SET SCHEMA sep3",
                "spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_DELAY=-1;INIT=CREATE SCHEMA IF NOT EXISTS sep3;SET SCHEMA sep3",
                "spring.jpa.hibernate.ddl-auto=create-drop",
                "spring.jpa.show-sql=true",
                "spring.jpa.properties.hibernate.default_schema=sep3",
                "spring.jpa.properties.hibernate.format_sql = true",
                "spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.H2Dialect",
                "spring.jpa.properties.hibernate.globally_quoted_identifiers=true",
        }
)
public class SepT3ApplicationTests
{

    @Autowired
    private UserRegistry userRegistry;

    @Autowired
    private PostRegistry postRegistry;

    @Autowired
    private RatingsRegistry ratingsRegistry;

    @Autowired
    private DataSource dataSource;

    /*@Autowired
    private TestEntityManager entityManager;

    public void UserSetUp()
    {
        User user1 = new User();
        user1.setUsername("Test1Username");
        user1.setUserPass("Test1Password");
        user1.setRole("User");
        user1.setEmail("test1.email@gmail.com");
        user1.setFullName("TestName1");
        user1.setAddress("TestAddress1");
        user1.setPhoneNumber("+4500112233");
        user1.setRegisteredOn(LocalDateTime.of(2022,1,1,12,0,0));
        user1.setLastSeen(LocalDateTime.of(2022,1,1,13,0,0));

        User user2 = new User();
        user2.setUsername("TestUsername2");
        user2.setUserPass("TestPassword2");
        user2.setRole("User");
        user2.setEmail("test2.email@gmail.com");
        user2.setFullName("TestName2");
        user2.setAddress("TestAddress2");
        user2.setPhoneNumber("+4511223344");
        user1.setRegisteredOn(LocalDateTime.of(2022,1,2,12,0,0));
        user1.setLastSeen(LocalDateTime.of(2022,1,2,13,0,0));

        User user3 = new User();
        user3.setUsername("Test3Username");
        user3.setUserPass("Test3Password");
        user3.setRole("User");
        user3.setEmail("test3.email@gmail.com");
        user3.setFullName("TestName3");
        user3.setAddress("TestAddress3");
        user3.setPhoneNumber("+4522334455");
        user1.setRegisteredOn(LocalDateTime.of(2022,1,3,12,0,0));
        user1.setLastSeen(LocalDateTime.of(2022,1,3,13,0,0));

        userRegistry.save(user1);
        userRegistry.save(user2);
        userRegistry.save(user3);
    }

    public void PostSetUp()
    {
        Post post1 = new Post();
        post1.setSellerid(userRegistry.findById(1).get());
        post1.setTitle("Title1Test");
        post1.setPictureUrl("Picture1Test");
        post1.setPrice(10);
        post1.setDescription("Description1Test");
        post1.setLocation("Location1Test");
        post1.setCreationDate(LocalDate.of(2022,1,1));

        Post post2 = new Post();
        post2.setSellerid(userRegistry.findById(2).get());
        post2.setTitle("Title2Test");
        post2.setPictureUrl("Picture2Test");
        post2.setPrice(20);
        post2.setDescription("Description2Test");
        post2.setLocation("Location2Test");
        post2.setCreationDate(LocalDate.of(2022,1,2));

        Post post3 = new Post();
        post3.setSellerid(userRegistry.findById(3).get());
        post3.setTitle("Title3Test");
        post3.setPictureUrl("Picture3Test");
        post3.setPrice(30);
        post3.setDescription("Description3Test");
        post3.setLocation("Location3Test");
        post3.setCreationDate(LocalDate.of(2022,1,3));

        postRegistry.save(post1);
        postRegistry.save(post2);
        postRegistry.save(post3);

    }

    public void RatingSetUp()
    {
        Rating rating1 = new Rating();
        rating1.setRatingValue(2);
        rating1.setUserFrom(userRegistry.findById(1).get());
        rating1.setUserFrom(userRegistry.findById(2).get());

        Rating rating2 = new Rating();
        rating1.setRatingValue(3);
        rating1.setUserFrom(userRegistry.findById(2).get());
        rating1.setUserFrom(userRegistry.findById(3).get());

        Rating rating3 = new Rating();
        rating1.setRatingValue(3);
        rating1.setUserFrom(userRegistry.findById(3).get());
        rating1.setUserFrom(userRegistry.findById(1).get());

        ratingsRegistry.save(rating1);
        ratingsRegistry.save(rating2);
        ratingsRegistry.save(rating3);
    }

    @BeforeEach
    public void SetUp()
    {
        UserSetUp();
        PostSetUp();
        RatingSetUp();
    }


    /*@Test
    public void saveUserTest()
    {
        User user4 = new User();
        user4.setUsername("Test4Username");
        user4.setUserPass("Test4Password");
        user4.setRole("User");
        user4.setEmail("test4.email@gmail.com");
        user4.setFullName("Test4Name");
        user4.setAddress("TestAddress4");
        user4.setPhoneNumber("+4511111111");



        User found = userRegistry.findByUsernameAndUserPass(user4.getUsername(), user4.getUserPass()).get();

        assertThat(user4.getUsername()).isEqualTo(found.getUsername());
    }


    @Test
    public void readAllUsersTest()
    {
        //Values initialised in the setUp()

        ArrayList<User> found = new ArrayList<>();
        userRegistry.findAll().forEach(found::add);

        assertThat(found.get(0).getUsername()).isEqualTo("Test1Username");
        assertThat(found.get(1).getUsername()).isEqualTo("Test2Username");
        assertThat(found.get(2).getUsername()).isEqualTo("Test3Username");
    }


    @Test
    public void getUserByIdTest()
    {
        //Values initialised in the UserSetUp()


        assertThat("Test2Username").isEqualTo(userRegistry.findById(2).get().getUsername());
    }


    @Test
    public void deleteUserByIdTest()
    {
        //Values initialised in the UserSetUp()

        userRegistry.deleteById(1);

        ArrayList<User> found = new ArrayList<>();
        userRegistry.findAll().forEach(found::add);

        //user1 has been deleted, so in the array "Test2Username" should be at index 0
        assertThat(found.get(0)).isEqualTo("Test2Username");
    }

    @Test
    public void updateUserPassByIdTest()
    {
        //Values initialised in the UserSetUp()

        userRegistry.updateUserPassById("NewPassword",1);

        User found = userRegistry.findById(1).get();

        assertThat(found.getUserPass()).isEqualTo("NewPassword");
    }

    @Test
    public void updateUserInformationTest()
    {
        //Values initialised in the UserSetUp()

        userRegistry.updateUserInformation(
                "NewUsername",
                "NewFullName",
                "new.email@gmail.com",
                "+4500000000",
                "NewAddress",
                "User",
                1
        );

        User found = userRegistry.findById(1).get();
        assertThat(found.getUsername()).isEqualTo("NewUsername");
        assertThat(found.getFullName()).isEqualTo("NewFullName");
        assertThat(found.getEmail()).isEqualTo("new.email@gmail.com");
        assertThat(found.getPhoneNumber()).isEqualTo("+4500000000");
        assertThat(found.getAddress()).isEqualTo("NewAddress");
    }

    // POST tests
    @Test
    public void savePostTest()
    {
        Post post4 = new Post();
        post4.setSellerid(userRegistry.findById(3).get());
        post4.setPictureUrl("Picture4Test");
        post4.setPrice(40);
        post4.setDescription("Description4Test");
        post4.setLocation("Location4Test");
        post4.setCreationDate(LocalDate.of(2022,1,3));

        Post found = postRegistry.findById(4).get();

        assertThat(post4.getSellerid()).isEqualTo(found.getSellerid());
        assertThat(post4.getTitle()).isEqualTo(found.getTitle());
        assertThat(post4.getPictureUrl()).isEqualTo(found.getPictureUrl());
        assertThat(post4.getPrice()).isEqualTo(found.getPrice());
        assertThat(post4.getDescription()).isEqualTo(found.getDescription());
        assertThat(post4.getLocation()).isEqualTo(found.getLocation());
        assertThat(post4.getCreationDate()).isEqualTo(found.getCreationDate());
        assertThat(post4.getSellerid()).isEqualTo(found.getSellerid());
    }

    @Test
    public void readAllPostsTest()
    {
        //Values initialised in the PostSetUp()

        ArrayList<Post> found = new ArrayList<>();
        postRegistry.findAll().forEach(found::add);

        assertThat(found.get(0).getSellerid().getUsername()).isEqualTo("Test1Username");
        assertThat(found.get(0).getTitle()).isEqualTo("Title1Test");
        assertThat(found.get(0).getPictureUrl()).isEqualTo("Picture1Test");
        assertThat(found.get(0).getPrice()).isEqualTo(10);
        assertThat(found.get(0).getDescription()).isEqualTo("Description1Test");
        assertThat(found.get(0).getLocation()).isEqualTo("Location1Test");
        assertThat(found.get(0).getCreationDate()).isEqualTo(LocalDate.of(2022,1,1));
    }

    @Test
    public void getPostByIdTest()
    {
        //Values initialised in the PostSetUp()

        Post found = postRegistry.findById(1).get();

        assertThat(found.getSellerid().getUsername()).isEqualTo("Test2Username");
        assertThat(found.getTitle()).isEqualTo("Title1Test");
        assertThat(found.getPictureUrl()).isEqualTo("Picture1Test");
        assertThat(found.getPrice()).isEqualTo(10);
        assertThat(found.getDescription()).isEqualTo("Description1Test");
        assertThat(found.getLocation()).isEqualTo("Location1Test");
        assertThat(found.getCreationDate()).isEqualTo(LocalDate.of(2022,1,1));
    }

    @Test
    public void deletePostByIdTest()
    {
        //Values initialised in the PostSetUp()

        postRegistry.deleteById(1);

        ArrayList<Post> found = new ArrayList<>();
        postRegistry.findAll().forEach(found::add);

        //post2 has been deleted, so in the array post2 should be at index 0
        assertThat(found.get(0).getSellerid().getUsername()).isEqualTo("Test2Username");
        assertThat(found.get(0).getTitle()).isEqualTo("Title2Test");
        assertThat(found.get(0).getPictureUrl()).isEqualTo("Picture2Test");
        assertThat(found.get(0).getPrice()).isEqualTo(20);
        assertThat(found.get(0).getDescription()).isEqualTo("Description2Test");
        assertThat(found.get(0).getLocation()).isEqualTo("Location2Test");
        assertThat(found.get(0).getCreationDate()).isEqualTo(LocalDate.of(2022,1,2));
    }

    @Test
    public void updatePostByIdTest()
    {
        postRegistry.updatePostById(
                "NewDescription",
                "NewLocation",
                null,
                "NewPicture",
                15,
                "NewTitle",
                1
        );

        Post found = postRegistry.findById(1).get();

        assertThat(found.getTitle()).isEqualTo("NewTitle");
        assertThat(found.getPictureUrl()).isEqualTo("NewPicture");
        assertThat(found.getPrice()).isEqualTo(15);
        assertThat(found.getDescription()).isEqualTo("NewDescription");
        assertThat(found.getLocation()).isEqualTo("NewLocation");
    }

    // RATING tests
    @Test
    public void saveRatingTest()
    {

    }
}
 */
