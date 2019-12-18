package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Comments;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Reviews;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends CrudRepository<Reviews,Integer> {

    List<Reviews> findAllByProduct_ProductId(int productId);

}
