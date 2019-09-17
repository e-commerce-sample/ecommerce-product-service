package com.ecommerce.product.sdk.event.product;

import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class ProductNameUpdatedEvent extends ProductEvent {
    private String oldName;
    private String newName;

    @ConstructorProperties({"productId", "oldName", "newName"})
    public ProductNameUpdatedEvent(String productId, String oldName, String newName) {
        super(productId);
        this.oldName = oldName;
        this.newName = newName;
    }

}
