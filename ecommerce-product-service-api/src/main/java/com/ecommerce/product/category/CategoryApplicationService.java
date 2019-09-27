package com.ecommerce.product.category;

import com.ecommerce.product.sdk.command.category.CreateCategoryCommand;
import com.ecommerce.product.sdk.representation.category.CategoryRepresentation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CategoryApplicationService {

    private CategoryRepository repository;

    public CategoryApplicationService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public String create(CreateCategoryCommand command) {
        Category category = Category.create(command.getName(), command.getDescription());
        repository.save(category);
        return category.getId();
    }

    @Transactional(readOnly = true)
    public CategoryRepresentation byId(String id) {
        return repository.byId(id).toRepresentation();
    }
}
