package com.ecommerce.product.sdk.event.product;

import com.ecommerce.shared.event.DomainEvent;
import lombok.Value;

@Value
public class ProductNameUpdatedEvent extends DomainEvent {
    private String productId;
    private String oldName;
    private String newName;

}
