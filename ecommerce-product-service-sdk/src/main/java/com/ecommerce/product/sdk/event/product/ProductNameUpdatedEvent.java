package com.ecommerce.product.sdk.event.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ProductNameUpdatedEvent extends ProductEvent {
    private String oldName;
    private String newName;

    @JsonCreator
    public ProductNameUpdatedEvent(@JsonProperty("productId") String productId,
                                   @JsonProperty("oldName") String oldName,
                                   @JsonProperty("newName") String newName) {
        super(productId);
        this.oldName = oldName;
        this.newName = newName;
    }

}
