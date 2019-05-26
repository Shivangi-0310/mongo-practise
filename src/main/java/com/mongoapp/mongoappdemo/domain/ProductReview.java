package com.mongoapp.mongoappdemo.domain;

public class ProductReview {

    private String userName;

    private Integer rating;

    public ProductReview(){
        super();
    }

    public ProductReview(String userName, Integer rating) {
        this.userName = userName;
        this.rating = rating;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getRating() {
        return rating;
    }
}
