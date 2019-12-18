package com.udacity.course3.reviews;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Reviews;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewsRepository;
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
public class ReviewRepositoryTests {
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
    @Test
    public void injectedComponentsAreNotNull(){
        assertThat(dataSource,is(notNullValue()));
        assertThat(jdbcTemplate,is(notNullValue()));
        assertThat(jdbcTemplate,is(notNullValue()));
        assertThat(entityManager,is(notNullValue()));
        assertThat(testEntityManager,is(notNullValue()));
        assertThat(productRepository,is(notNullValue()));
        assertThat(reviewRepository,is(notNullValue()));
    }

    public Product saveProduct(String name){
        Product product = new Product();
        product.setProductName(name);
        Product product1=productRepository.save(product);
        return product1;
    }

    @Test
    public void testSaveReview(){
        String productName="product1";
        Product product=saveProduct(productName);
        Reviews reviews=new Reviews();
        reviews.setProduct(product);
        reviews.setReviewContent("Review 1 for product1");
        Reviews reviews1=reviewRepository.save(reviews);
        assertEquals("review content should be matching",reviews.getReviewContent(),reviews1.getReviewContent());
        assertNotEquals("valid review id should be present",0,reviews1.getReviewId());
        assertEquals("product content should be matching",reviews.getProduct().getProductName(),reviews1.getProduct().getProductName());

    }
    @Test
    public void testGetReviewsForProduct(){

        //save two reviews for product2 and retrieve the list
        String productName="product2";
        Product product=saveProduct(productName);
        Reviews reviews=new Reviews();
        reviews.setProduct(product);
        reviews.setReviewContent("Review 1 for product2");
        reviewRepository.save(reviews);
        reviews=new Reviews();
        reviews.setProduct(product);
        reviews.setReviewContent("Review 2 for product2");
        reviewRepository.save(reviews);

        List<Reviews> list=reviewRepository.findAllByProduct_ProductId(product.getProductId());
        assertEquals("Two saved reviews for product must be returned",2,list.size());

    }
}
