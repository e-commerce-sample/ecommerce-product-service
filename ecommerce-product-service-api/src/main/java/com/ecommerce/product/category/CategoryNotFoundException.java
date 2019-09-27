package com.ecommerce.product.category;

import com.ecommerce.shared.exception.AppException;

import static com.ecommerce.product.ProductErrorCode.CATEGORY_NOT_FOUND;
import static com.google.common.collect.ImmutableMap.of;


public class CategoryNotFoundException extends AppException {
    public CategoryNotFoundException(String id) {
        super(CATEGORY_NOT_FOUND, of("productId", id));
    }
}
