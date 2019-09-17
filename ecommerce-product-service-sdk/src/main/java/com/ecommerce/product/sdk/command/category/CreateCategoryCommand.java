package com.ecommerce.product.sdk.command.category;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class CreateCategoryCommand {
    @NotBlank(message = "产品目录名字不能为空")
    private String name;

    @NotBlank(message = "产品目录描述不能为空")
    private String description;

}
