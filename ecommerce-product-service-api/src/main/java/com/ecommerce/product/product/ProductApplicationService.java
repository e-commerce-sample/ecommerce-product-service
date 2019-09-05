package com.ecommerce.product.product;

import com.ecommerce.product.command.product.CreateProductCommand;
import com.ecommerce.product.command.product.UpdateProductNameCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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
        log.info("Created product[{}].", product.getId());
        return product.getId();
    }

    @Transactional
    public ProductId updateProductName(String productId, UpdateProductNameCommand command) {
        Product product = productRepository.byId(ProductId.of(productId));
        product.updateName(command.getNewName());
        productRepository.save(product);
        log.info("Updated name to {} for product[{}].", product.getName(), product.getId());
        return product.getId();
    }
}
