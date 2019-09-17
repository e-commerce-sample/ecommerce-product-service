package com.ecommerce.product.sdk.command.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class CreateProductCommand {

    @NotBlank(message = "产品名字不能为空")
    private final String name;

    @NotBlank(message = "产品描述不能为空")
    private final String description;

    @NotNull(message = "产品价格不能为空")
    private final BigDecimal price;

    @NotBlank(message = "产品所属目录不能为空")
    private final String categoryId;

    @JsonCreator
    public CreateProductCommand(@JsonProperty("name") String name,
                                @JsonProperty("description") String description,
                                @JsonProperty("price") BigDecimal price,
                                @JsonProperty("categoryId") String categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }

}
