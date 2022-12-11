package via.sdj3.sep_t3.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import via.sdj3.sep_t3.model.Category;
import via.sdj3.sep_t3.model.Post;

/**
 * Post registry it will post a post in registry and extends the crud
 */
public interface PostRegistry extends CrudRepository<Post,Integer>
{
    /**
     * Update post by id.
     *
     * @param description the description
     * @param location    the location
     * @param category    the category
     * @param pictureUrl  the picture url
     * @param price       the price
     * @param title       the title
     * @param status      the status
     * @param id          the id
     */
    @Transactional
    @Modifying
    @Query("""
            update posts p set p.description = ?1, p.location = ?2, p.category = ?3, p.pictureUrl = ?4, p.price = ?5, p.title = ?6 , p.status = ?7
            where p.id = ?8""")
    void updatePostById(String description, String location, Category category, String pictureUrl, Integer price, String title,Integer status, Integer id);
}
