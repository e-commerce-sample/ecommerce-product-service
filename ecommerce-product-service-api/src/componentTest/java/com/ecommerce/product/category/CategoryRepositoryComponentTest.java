package com.ecommerce.product.category;

import com.ecommerce.product.BaseComponentTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryRepositoryComponentTest extends BaseComponentTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void should_save_category() {
        Category category = Category.create("books", "all about books");
        repository.save(category);
        Category saved = repository.byId(category.getId());
        assertEquals(category.getId(), saved.getId());
    }
}
