package com.udacity.course3.reviews.entity;

import javax.persistence.*;

@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="commentId")
    private int commentId;
    @ManyToOne
    @JoinColumn(name="reviewId", nullable=false, updatable=false)
    private Reviews reviews;
    @Column(name="reviewComments")
    private String reviewComments;

    public Comments() {
    }

    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
    }

    public String getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(String reviewComments) {
        this.reviewComments = reviewComments;
    }

    public Comments(int commentId, Reviews reviews, String reviewComments) {
        this.commentId = commentId;
        this.reviews = reviews;
        this.reviewComments = reviewComments;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
}
