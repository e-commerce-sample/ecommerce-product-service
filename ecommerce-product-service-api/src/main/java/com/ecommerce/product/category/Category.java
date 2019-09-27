package com.ecommerce.product.category;

import com.ecommerce.product.sdk.event.category.CategoryCreatedEvent;
import com.ecommerce.product.sdk.representation.category.CategoryRepresentation;
import com.ecommerce.shared.model.BaseAggregate;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

import static com.ecommerce.shared.utils.UuidGenerator.newUuid;

@Builder
@Getter
public class Category extends BaseAggregate {
    private String id;
    private String name;
    private String description;
    private Instant createdAt;

    public static Category create(String name, String description) {
        String id = newUuid();
        Category category = builder()
                .id(id)
                .name(name)
                .description(description)
                .createdAt(Instant.now())
                .build();
        category.raiseEvent(new CategoryCreatedEvent(id, name, description));
        return category;
    }

    public CategoryRepresentation toRepresentation() {
        return new CategoryRepresentation(this.id,
                this.name,
                this.description,
                this.createdAt);
    }
}
