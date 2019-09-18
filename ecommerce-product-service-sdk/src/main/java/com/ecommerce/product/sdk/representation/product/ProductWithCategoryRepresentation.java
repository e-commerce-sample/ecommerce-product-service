package com.ecommerce.product.sdk.representation.product;


import lombok.Value;

@Value
public class ProductWithCategoryRepresentation {
    private String id;
    private String name;
    private String categoryId;
    private String categoryName;
}
