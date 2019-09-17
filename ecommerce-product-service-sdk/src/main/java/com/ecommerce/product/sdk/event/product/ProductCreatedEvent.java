package com.ecommerce.product.sdk.event.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;


@Getter
public class ProductCreatedEvent extends ProductEvent {
    private String name;
    private String description;
    private BigDecimal price;
    private Instant createdAt;

    @JsonCreator
    public ProductCreatedEvent(@JsonProperty("productId") String productId,
                               @JsonProperty("name") String name,
                               @JsonProperty("description") String description,
                               @JsonProperty("price") BigDecimal price,
                               @JsonProperty("createdAt") Instant createdAt) {
        super(productId);
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
    }

}
