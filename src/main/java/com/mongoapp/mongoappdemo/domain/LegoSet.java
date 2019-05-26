package com.mongoapp.mongoappdemo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Collection;

@Document(collection = "legoSet")
public class LegoSet {

    @Id
    private String id;

    private String name;

    private LegoSetDifficulty difficulty;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String theme;

    @Field("delivery")
    private DeliveryInfo deliveryInfo;

    private Collection<ProductReview> productReviews = new ArrayList<>();

    public LegoSet(){
        super();
    }

    public LegoSet(String name, LegoSetDifficulty difficulty, String theme,
                   DeliveryInfo deliveryInfo, Collection<ProductReview> productReviews) {
        this.name = name;
        this.difficulty = difficulty;
        this.theme = theme;
        this.deliveryInfo = deliveryInfo;
        if (productReviews != null) {
            this.productReviews = productReviews;
        }
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LegoSetDifficulty getDifficulty() {
        return difficulty;
    }

    public String getTheme() {
        return theme;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public Collection<ProductReview> getProductReviews() {
        return productReviews;
    }
}
