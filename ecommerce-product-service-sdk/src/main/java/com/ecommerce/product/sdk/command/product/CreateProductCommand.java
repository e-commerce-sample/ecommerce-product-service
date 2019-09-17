package com.ecommerce.product.sdk.command.product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateProductCommand {

    @NotBlank(message = "产品名字不能为空")
    private String name;

    @NotBlank(message = "产品描述不能为空")
    private String description;

    @NotNull(message = "产品价格不能为空")
    private BigDecimal price;

    @NotBlank(message = "产品所属目录不能为空")
    private String categoryId;

}
