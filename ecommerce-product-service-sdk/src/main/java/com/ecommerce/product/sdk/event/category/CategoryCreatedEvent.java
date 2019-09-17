package com.ecommerce.product.sdk.event.category;

import com.ecommerce.shared.event.DomainEvent;
import lombok.Value;

@Value
public class CategoryCreatedEvent extends DomainEvent {
    private String categoryId;
    private String name;
    private String description;
}
