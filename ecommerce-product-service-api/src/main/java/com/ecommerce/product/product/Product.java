package com.ecommerce.product.product;


import com.ecommerce.product.sdk.event.product.ProductCreatedEvent;
import com.ecommerce.product.sdk.event.product.ProductNameUpdatedEvent;
import com.ecommerce.product.sdk.representation.product.ProductRepresentation;
import com.ecommerce.shared.model.BaseAggregate;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

import static com.ecommerce.shared.utils.UuidGenerator.newUuid;

@Builder
@Getter
public class Product extends BaseAggregate {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Instant createdAt;
    private int inventory;
    private String categoryId;

    public static Product create(String name, String description, BigDecimal price, String categoryId) {
        Product product = Product.builder()
                .id(newUuid())
                .name(name)
                .description(description)
                .price(price)
                .createdAt(Instant.now())
                .inventory(0)
                .categoryId(categoryId)
                .build();
        product.raiseEvent(new ProductCreatedEvent(product.getId(), name, description, price, product.getCreatedAt()));
        return product;
    }

    public void updateName(String newName) {
        raiseEvent(new ProductNameUpdatedEvent(this.getId(), name, newName));
        this.name = newName;
    }

    public void updateInventory(int inventory) {
        this.inventory = inventory;
    }

    public ProductRepresentation toRepresentation() {
        return new ProductRepresentation(id,
                name,
                description,
                price,
                createdAt,
                inventory,
                categoryId);
    }
}
