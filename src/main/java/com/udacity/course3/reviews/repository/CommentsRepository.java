package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Comments;
import com.udacity.course3.reviews.entity.Reviews;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends CrudRepository<Comments,Integer> {
    List<Comments> findAllByReviews_ReviewId(int reviewId);

}
