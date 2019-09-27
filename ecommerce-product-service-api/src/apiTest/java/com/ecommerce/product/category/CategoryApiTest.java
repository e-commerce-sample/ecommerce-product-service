package com.ecommerce.product.category;

import com.ecommerce.product.BaseApiTest;
import com.ecommerce.product.sdk.command.category.CreateCategoryCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryApiTest extends BaseApiTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void should_create_category() {
        String id = given()
                .contentType("application/json")
                .body(new CreateCategoryCommand("books", "all about books"))
                .when()
                .post("/categories")
                .then().statusCode(201)
                .extract().body().jsonPath().getString("id");
        Category category = repository.byId(id);
        assertNotNull(category);
        assertEquals(id, category.getId());
    }


    @Test
    public void should_get_category_detail() {
        Category category = Category.create("cars", "all about cars");
        repository.save(category);
        given()
                .when().get("/categories/{id}", category.getId())
                .then().statusCode(200)
                .body("name", is("cars"));
    }


}