package com.ecommerce.product.sdk.event.category;

import com.ecommerce.shared.event.DomainEvent;
import lombok.Getter;

@Getter
public abstract class CategoryEvent extends DomainEvent {
    private String categoryId;

    protected CategoryEvent(String categoryId) {
        this.categoryId = categoryId;
    }

}
