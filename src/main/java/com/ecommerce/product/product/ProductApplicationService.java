package com.ecommerce.product.product;

import com.ecommerce.common.logging.AutoNamingLoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductApplicationService {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    private final ProductRepository productRepository;

    public ProductApplicationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductId create(CreateProductCommand command) {
        Product product = Product.create(command.getName(), command.getDescription(), command.getPrice());
        productRepository.save(product);
        logger.info("Created product[{}].", product.getId());
        return product.getId();
    }

    @Transactional
    public ProductId updateProductName(String productId, UpdateProductNameCommand command) {
        Product product = productRepository.byId(ProductId.of(productId));
        product.updateName(command.getNewName());
        productRepository.save(product);
        logger.info("Updated name to {} for product[{}].", product.getName(), product.getId());
        return product.getId();
    }
}
