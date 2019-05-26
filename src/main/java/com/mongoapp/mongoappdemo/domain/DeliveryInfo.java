package com.mongoapp.mongoappdemo.domain;


import java.time.LocalDate;

public class DeliveryInfo {

    private LocalDate deliveryDate;

    private Integer deliveryFee;

    private Boolean inStock;

    public DeliveryInfo(){
        super();
    }

    public DeliveryInfo(LocalDate deliveryDate, Integer deliveryFee, Boolean inStock) {
        this.deliveryDate = deliveryDate;
        this.deliveryFee = deliveryFee;
        this.inStock = inStock;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public Integer getDeliveryFee() {
        return deliveryFee;
    }

    public Boolean getInStock() {
        return inStock;
    }
}
