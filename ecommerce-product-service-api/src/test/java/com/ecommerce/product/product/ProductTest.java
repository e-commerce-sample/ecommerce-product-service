package com.ecommerce.product.product;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductTest {

    @Test
    public void should_create_product() {
        Product product = Product.create("name", "desc", BigDecimal.valueOf(12.0), "123456");
        assertNotNull(product);
    }
}