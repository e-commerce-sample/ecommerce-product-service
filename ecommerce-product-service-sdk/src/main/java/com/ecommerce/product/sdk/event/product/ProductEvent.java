package com.ecommerce.product.sdk.event.product;

import com.ecommerce.shared.event.DomainEvent;
import lombok.Getter;

@Getter
public abstract class ProductEvent extends DomainEvent {
    private String productId;

    protected ProductEvent(String productId) {
        this.productId = productId;
    }

}
