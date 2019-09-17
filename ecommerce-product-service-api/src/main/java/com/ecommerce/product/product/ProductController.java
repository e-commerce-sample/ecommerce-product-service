package com.ecommerce.product.product;

import com.ecommerce.product.command.product.CreateProductCommand;
import com.ecommerce.product.command.product.UpdateProductNameCommand;
import com.ecommerce.product.representation.product.ProductSummaryRepresentation;
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
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductApplicationService productApplicationService;
    private final ProductRepresentationService productRepresentationService;

    public ProductController(ProductApplicationService productApplicationService,
                             ProductRepresentationService productRepresentationService) {
        this.productApplicationService = productApplicationService;
        this.productRepresentationService = productRepresentationService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Map<String, String> createProduct(@RequestBody @Valid CreateProductCommand command) {
        return of("id", productApplicationService.create(command));
    }


    @PutMapping("/{id}/name")
    public Map<String, String> updateProductName(@PathVariable("id") String productId,
                                                 @RequestBody @Valid UpdateProductNameCommand command) {
        return of("id", productApplicationService.updateProductName(productId, command));
    }


    @GetMapping
    public PagedResource<ProductSummaryRepresentation> pagedProducts(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                                                                     @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return productRepresentationService.listProducts(pageIndex, pageSize);
    }


    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public Object byId(@PathVariable(name = "id") String id) {
        return productRepresentationService.byId(id);
    }

}
