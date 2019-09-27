package com.ecommerce.product.sdk.representation.category;

import lombok.Value;

import java.time.Instant;

@Value
public class CategoryRepresentation {
    private String id;
    private String name;
    private String description;
    private Instant createdAt;
}
