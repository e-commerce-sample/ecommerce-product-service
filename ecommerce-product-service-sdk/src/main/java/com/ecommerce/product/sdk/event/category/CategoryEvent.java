package com.ecommerce.product.sdk.event.category;

import com.ecommerce.shared.event.DomainEvent;
import lombok.Getter;

@Getter
public class CategoryEvent extends DomainEvent {
    private String id;

    public CategoryEvent(String id) {
        this.id = id;
    }
}
