package com.ecommerce.product.sdk.command.product;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class UpdateProductNameCommand {

    @NotBlank(message = "产品名字不能为空")
    private String newName;

}
