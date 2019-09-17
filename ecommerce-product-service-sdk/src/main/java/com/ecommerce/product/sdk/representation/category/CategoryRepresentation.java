package com.ecommerce.product.sdk.representation.category;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class CategoryRepresentation {
    private String id;
    private String name;
    private String description;
    private Instant createdAt;
}
