package com.ecommerce.product.command.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CreateCategoryCommand {
    @NotBlank(message = "产品目录名字不能为空")
    private final String name;

    @NotBlank(message = "产品目录描述不能为空")
    private final String description;


    @JsonCreator
    public CreateCategoryCommand(@JsonProperty("name") String name,
                                 @JsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }
}
