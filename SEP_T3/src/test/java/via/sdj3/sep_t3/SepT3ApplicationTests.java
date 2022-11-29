package via.sdj3.sep_t3;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import via.sdj3.sep_t3.model.Category;
import via.sdj3.sep_t3.model.Post;
import via.sdj3.sep_t3.model.User;
import via.sdj3.sep_t3.repository.PostRegistry;
import via.sdj3.sep_t3.repository.UserRegistry;
import static org.assertj.core.api.Assertions.*;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(
        properties = {
                "spring.jpa.hibernate.ddl-auto  = create-drop",
                "spring.jpa.show-sql=true",
                "spring.jpa.properties.hibernate.format_sql = true"
        }
)
class SepT3ApplicationTests
{
    @Autowired
    private UserRegistry userRegistry;

    @Autowired
    private PostRegistry postRegistry;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void UserSetUp()
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

    @BeforeEach
    void PostCategorySetUp()
    {
        Post post1 = new Post();
        post1.setSellerid(userRegistry.findById(1).get());
        post1.setPictureUrl("Picture1Test");
        post1.setPrice(10);
        post1.setDescription("Description1Test");
        post1.setLocation("Location1Test");
        post1.setCreationDate(LocalDate.of(2022,1,1));
        Category cat1 = new Category();
        cat1.setId(1);
        cat1.setDescription("Category1Test");
        cat1.setPosts();

        Post post2 = new Post();
        post1.setSellerid(userRegistry.findById(2).get());
        post1.setPictureUrl("Picture2Test");
        post1.setPrice(20);
        post1.setDescription("Description2Test");
        post1.setLocation("Location2Test");
        post1.setCreationDate(LocalDate.of(2022,1,2));
        Category cat2 = new Category();
        cat1.setId(2);
        cat1.setDescription("Category2Test");
        post1.setCategory(cat2);

        Post post3 = new Post();
        post1.setSellerid(userRegistry.findById(3).get());
        post1.setPictureUrl("Picture3Test");
        post1.setPrice(30);
        post1.setDescription("Description3Test");
        post1.setLocation("Location3Test");
        post1.setCreationDate(LocalDate.of(2022,1,3));
        Category cat3 = new Category();
        cat1.setId(3);
        cat1.setDescription("Category3Test");
        post1.setCategory(cat3);

        postRegistry.save(post1);
        postRegistry.save(post2);
        postRegistry.save(post3);

    }

    //USER Tests
    @Test
    private void saveUserTest()
    {
        User user4 = new User();
        user4.setUsername("Test4Username");
        user4.setUserPass("Test4Password");
        user4.setRole("User");
        user4.setEmail("test4.email@gmail.com");
        user4.setFullName("Test4Name");
        user4.setAddress("TestAddress4");
        user4.setPhoneNumber("+4511111111");

        entityManager.persist(user4);

        User found = userRegistry.findByUsernameAndUserPass(user4.getUsername(), user4.getUserPass()).get();

        assertThat(user4.getUsername()).isEqualTo(found.getUsername());
    }

    @Test
    private void readAllUsersTest()
    {
        //Values initialised in the setUp()

        ArrayList<User> found = new ArrayList<>();
        userRegistry.findAll().forEach(found::add);

        assertThat(found.get(0).getUsername()).isEqualTo("Test1Username");
        assertThat(found.get(1).getUsername()).isEqualTo("Test2Username");
        assertThat(found.get(2).getUsername()).isEqualTo("Test3Username");
    }

    @Test
    private void getUserByIdTest()
    {
        //Values initialised in the UserSetUp()

        assertThat("Test2Username").isEqualTo(userRegistry.findById(2).get().getUsername());
    }

    @Test
    private void deleteUserByIdTest()
    {
        //Values initialised in the UserSetUp()

        userRegistry.deleteById(1);

        ArrayList<User> found = new ArrayList<>();
        userRegistry.findAll().forEach(found::add);

        //Test1User has been deleted, so in the array "Test2Username" should be at index 0
        assertThat(found.get(0)).isEqualTo("Test2Username");
    }

    @Test
    private void updateUserPassByIdTest()
    {
        //Values initialised in the UserSetUp()

        userRegistry.updateUserPassById("NewPassword",1);

        User found = userRegistry.findById(1).get();

        assertThat(found.getUserPass()).isEqualTo("NewPassword");
    }

    @Test
    private void updateUserInformationTest()
    {
        //Values initialised in the UserSetUp()

        userRegistry.updateUserInformation(
                "NewUsername",
                "NewFullName",
                "new.email@gmail.com",
                "+4500000000",
                "NewAddress",
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
    private void savePostTest()
    {
        Post post4 = new Post(); // this post will have is ID set by the database to 4
        post4.setSellerid(userRegistry.findById(3).get());
        post4.setPictureUrl("Picture3Test");
        post4.setPrice(30);
        post4.setDescription("Description3Test");
        post4.setLocation("Location3Test");
        post4.setCreationDate(LocalDate.of(2022,1,3));
        Category cat4 = new Category();
        cat4.setId(4);
        cat4.setDescription("Category4Test");
        post4.setCategory(cat4);

        entityManager.persist(post4);

        Post found = postRegistry.findById(4).get();

        assertThat(post4.getSellerid()).isEqualTo(found.getSellerid());
        assertThat(post4.getPictureUrl()).isEqualTo(found.getPictureUrl());
        assertThat(post4.getPrice()).isEqualTo(found.getPrice());
        assertThat(post4.getDescription()).isEqualTo(found.getDescription());
        assertThat(post4.getLocation()).isEqualTo(found.getLocation());
        assertThat(post4.getCreationDate()).isEqualTo(found.getCreationDate());
        assertThat(post4.getSellerid()).isEqualTo(found.getSellerid());
    }
}
