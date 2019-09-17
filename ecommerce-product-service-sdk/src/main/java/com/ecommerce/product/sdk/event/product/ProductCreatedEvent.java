package com.ecommerce.product.sdk.event.product;

import lombok.Getter;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class ProductCreatedEvent extends ProductEvent {
    private String name;
    private String description;
    private BigDecimal price;
    private Instant createdAt;


    @ConstructorProperties({"productId", "name", "description", "price", "createdAt"})
    public ProductCreatedEvent(String productId, String name, String description, BigDecimal price, Instant createdAt) {
        super(productId);
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
    }
}
