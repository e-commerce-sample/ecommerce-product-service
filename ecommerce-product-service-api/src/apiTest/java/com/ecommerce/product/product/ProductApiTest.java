package com.ecommerce.product.product;

import com.ecommerce.product.BaseApiTest;
import com.ecommerce.product.command.product.CreateProductCommand;
import com.ecommerce.product.command.product.UpdateProductNameCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ecommerce.product.product.Product.create;
import static java.math.BigDecimal.valueOf;
import static java.util.stream.IntStream.range;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductApiTest extends BaseApiTest {

    @Autowired
    private ProductRepository repository;

    @Test
    public void should_create_product() {
        String id = given()
                .contentType("application/json")
                .body(new CreateProductCommand("喜乐多", "喜乐多真好喝", valueOf(2)))
                .when()
                .post("/products")
                .then().statusCode(201)
                .extract().body().jsonPath().getString("id");
        Product product = repository.byId(id);
        assertNotNull(product);
        assertEquals(id, product.getId());
    }

    @Test
    public void should_list_product_summary() {
        range(0, 10).forEach(value -> repository.save(create("喜乐多", "喜乐多真好喝", valueOf(5))));
        given()
                .when()
                .get("/products?pageIndex=2&pageSize=5")
                .then().statusCode(200)
                .body("pageIndex", is(2))
                .body("resource.size()", is(5));
    }

    @Test
    public void should_get_product_detail() {
        Product product = create("喜茶", "喜茶", valueOf(10));
        repository.save(product);
        given()
                .when().get("/products/{id}", product.getId())
                .then().statusCode(200)
                .body("name", is("喜茶"));
    }


    @Test
    public void should_update_product_name() {
        Product product = create("喜茶", "喜茶", valueOf(10));
        repository.save(product);
        given()
                .contentType("application/json")
                .body(new UpdateProductNameCommand("新喜茶"))
                .when()
                .put("/products/{id}/name", product.getId())
                .then().statusCode(200);

        Product updatedProduct = repository.byId(product.getId());
        assertEquals("新喜茶", updatedProduct.getName());

    }


}