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
}
