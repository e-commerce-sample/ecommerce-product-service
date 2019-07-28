package com.ecommerce.product.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class UpdateProductNameCommand {

    @NotBlank(message = "产品名字不能为空")
    private final String newName;

    @JsonCreator
    public UpdateProductNameCommand(@JsonProperty("newName") String newName) {
        this.newName = newName;
    }

    public String getNewName() {
        return newName;
    }
}
