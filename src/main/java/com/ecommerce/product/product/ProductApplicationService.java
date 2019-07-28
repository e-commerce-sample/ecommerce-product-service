package com.ecommerce.product.product;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductApplicationService {

    private final ProductRepository productRepository;

    public ProductApplicationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductId create(CreateProductCommand command) {
        Product product = Product.create(command.getName(), command.getDescription(), command.getPrice());
        productRepository.save(product);
        return product.getId();
    }

    @Transactional
    public ProductId updateProductName(String productId, UpdateProductNameCommand command) {
        Product product = productRepository.byId(ProductId.productId(productId));
        product.updateName(command.getNewName());
        productRepository.save(product);
        return product.getId();
    }
}
