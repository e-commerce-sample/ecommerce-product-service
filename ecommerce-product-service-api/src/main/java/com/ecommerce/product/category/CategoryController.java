package com.ecommerce.product.category;

import com.ecommerce.product.sdk.command.category.CreateCategoryCommand;
import com.ecommerce.product.sdk.representation.category.CategoryRepresentation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
    private CategoryApplicationService service;

    public CategoryController(CategoryApplicationService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Map<String, String> createCategory(@RequestBody @Valid CreateCategoryCommand command) {
        return of("id", service.create(command));
    }


    @GetMapping(value = "/{id}")
    public CategoryRepresentation byId(@PathVariable(name = "id") String id) {
        return service.byId(id);
    }


}
