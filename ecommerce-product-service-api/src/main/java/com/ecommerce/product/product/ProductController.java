package com.ecommerce.product.product;

import com.ecommerce.product.sdk.command.product.CreateProductCommand;
import com.ecommerce.product.sdk.command.product.UpdateProductNameCommand;
import com.ecommerce.product.sdk.representation.product.ProductRepresentation;
import com.ecommerce.product.sdk.representation.product.ProductSummaryRepresentation;
import com.ecommerce.product.sdk.representation.product.ProductWithCategoryRepresentation;
import com.ecommerce.shared.utils.PagedResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductApplicationService applicationService;
    private final ProductRepresentationService representationService;

    public ProductController(ProductApplicationService applicationService,
                             ProductRepresentationService representationService) {
        this.applicationService = applicationService;
        this.representationService = representationService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Map<String, String> createProduct(@RequestBody @Valid CreateProductCommand command) {
        return of("id", applicationService.create(command));
    }


    @PutMapping("/{id}/name")
    public Map<String, String> updateProductName(@PathVariable("id") String productId,
                                                 @RequestBody @Valid UpdateProductNameCommand command) {
        return of("id", applicationService.updateProductName(productId, command));
    }


    @GetMapping(value = "/{id}")
    public ProductRepresentation byId(@PathVariable(name = "id") String id) {
        return representationService.byId(id);
    }

    @GetMapping
    public PagedResource<ProductSummaryRepresentation> pagedProducts(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                                                                     @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return representationService.listProducts(pageIndex, pageSize);
    }


    @GetMapping(value = "/{id}/with-category")
    public ProductWithCategoryRepresentation pagedWithCategory(@PathVariable(name = "id") String id) {
        return representationService.productWithCategory(id);
    }

}
