package com.ecommerce.product.sdk.command.category;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateCategoryCommand {
    @NotBlank(message = "产品目录名字不能为空")
    private String name;

    @NotBlank(message = "产品目录描述不能为空")
    private String description;

}
