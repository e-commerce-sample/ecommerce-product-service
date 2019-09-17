package com.ecommerce.product.event.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CategoryCreatedEvent extends CategoryEvent {
    private String name;
    private String description;

    @JsonCreator
    public CategoryCreatedEvent(@JsonProperty("id") String id,
                                @JsonProperty("name") String name,
                                @JsonProperty("description") String description) {
        super(id);
        this.name = name;
        this.description = description;
    }
}
