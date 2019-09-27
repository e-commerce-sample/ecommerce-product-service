package com.ecommerce.product.sdk.representation.product;


import lombok.Value;

import java.math.BigDecimal;

@Value
public class ProductSummaryRepresentation {
    private String id;
    private String name;
    private BigDecimal price;

}
