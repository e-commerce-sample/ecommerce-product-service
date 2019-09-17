package com.ecommerce.product.product;


import com.ecommerce.product.event.product.ProductCreatedEvent;
import com.ecommerce.product.event.product.ProductNameUpdatedEvent;
import com.ecommerce.shared.model.BaseAggregate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

import static com.ecommerce.shared.utils.UuidGenerator.newUuid;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
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

}
