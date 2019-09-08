package com.ecommerce.product.product;

import com.ecommerce.shared.exception.AppException;
import com.google.common.collect.ImmutableMap;

import static com.ecommerce.product.ProductErrorCode.PRODUCT_NOT_FOUND;


public class ProductNotFoundException extends AppException {
    public ProductNotFoundException(String id) {
        super(PRODUCT_NOT_FOUND, ImmutableMap.of("productId", id));
    }
}
