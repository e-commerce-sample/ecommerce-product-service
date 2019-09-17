package com.ecommerce.product.sdk.event.product;

import com.ecommerce.shared.event.DomainEvent;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Value
public class ProductCreatedEvent extends DomainEvent {
    private String productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Instant createdAt;

}
