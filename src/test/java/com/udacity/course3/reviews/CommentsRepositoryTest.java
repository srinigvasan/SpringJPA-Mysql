package com.udacity.course3.reviews;
import com.udacity.course3.reviews.entity.Comments;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Reviews;
import com.udacity.course3.reviews.repository.CommentsRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewsRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentsRepositoryTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewsRepository reviewRepository;
    @Autowired
    private CommentsRepository commentsRepository;



    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(dataSource,is(notNullValue()));
        assertThat(jdbcTemplate,is(notNullValue()));
        assertThat(jdbcTemplate,is(notNullValue()));
        assertThat(entityManager,is(notNullValue()));
        assertThat(testEntityManager,is(notNullValue()));
        assertThat(productRepository,is(notNullValue()));
        assertThat(reviewRepository,is(notNullValue()));
        assertThat(commentsRepository,is(notNullValue()));
    }

    public Product saveProduct(String name){
        Product product = new Product();
        product.setProductName(name);
        Product product1=productRepository.save(product);
        return product1;
    }

    public Reviews setup(){
        Product product = saveProduct("product1");
        Reviews reviews=new Reviews();
        reviews.setReviewContent("Review for product1");
        reviews.setProduct(product);
        reviewRepository.save(reviews);
        return reviews;
    }

    @Test
    public void testSaveComments(){
        Reviews reviews=setup();
        Comments comments=new Comments();
        comments.setReviewComments("Comment1 for Review 1");
        comments.setReviews(reviews);
        Comments comments1=commentsRepository.save(comments);
        assertEquals("Review Comment should be matching",comments.getReviewComments(),comments1.getReviewComments());
        assertNotEquals("valid comment id should be present",0,comments1.getCommentId());

    }
    @Test
    public void testListComments(){
       //Save one more comment for review 1
        Reviews reviews=setup();
        Comments comments=new Comments();
        comments.setReviewComments("Comment1 for Review 1");
        comments.setReviews(reviews);
        Comments comments1=commentsRepository.save(comments);
        comments=new Comments();
        comments.setReviewComments("Comment2 for Review 1");
        comments.setReviews(reviews);
        commentsRepository.save(comments);
    // Validate if two review comments present
        List<Comments> commentsList=commentsRepository.findAllByReviews_ReviewId(reviews.getReviewId());
        assertEquals("Two saved comments for the reviews must be returned",2,commentsList.size());


    }
}
