package com.ecommerce.product.sdk.event.category;

import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class CategoryCreatedEvent extends CategoryEvent {
    private String name;
    private String description;

    @ConstructorProperties({"categoryId", "name", "description"})
    public CategoryCreatedEvent(String categoryId, String name, String description) {
        super(categoryId);
        this.name = name;
        this.description = description;
    }
}
