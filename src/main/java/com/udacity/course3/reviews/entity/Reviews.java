package com.udacity.course3.reviews.entity;

import javax.persistence.*;

@Entity
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reviewId")
    private int reviewId;
    @Column(name="reviewContent")
    private String reviewContent;
    @ManyToOne
    @JoinColumn(name="productId", nullable=false, updatable=false)
    private Product product;

    public Reviews() {
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Reviews(int reviewId, String reviewContent, Product product) {
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
        this.product = product;
    }
}
