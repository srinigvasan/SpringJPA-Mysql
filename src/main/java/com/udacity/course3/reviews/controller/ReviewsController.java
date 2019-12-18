package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Reviews;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    // TODO: Wire JPA repositories here
    @Autowired
      private ReviewsRepository reviewsRepository;
    @Autowired
      private ProductRepository productRepository;


    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<?> createReviewForProduct(@PathVariable("productId") Integer productId, @RequestBody Reviews reviews) {

        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            reviews.setProduct(optionalProduct.get());
            Reviews reviews1=reviewsRepository.save(reviews);
            return new ResponseEntity<Reviews>(reviews1,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Reviews>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        return new ResponseEntity<Iterable<Reviews>>(reviewsRepository.findAllByProduct_ProductId(productId),HttpStatus.OK);
    }
}