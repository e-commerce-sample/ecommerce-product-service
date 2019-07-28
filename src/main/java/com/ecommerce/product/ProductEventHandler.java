package com.ecommerce.product;

import com.ecommerce.common.logging.AutoNamingLoggerFactory;
import com.ecommerce.product.product.Product;
import com.ecommerce.product.product.ProductRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.ecommerce.product.product.ProductId.of;

@Component
public class ProductEventHandler {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    private ProductRepository repository;

    public ProductEventHandler(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void updateProductInventory(String productId, int remains) {
        Product product = repository.byId(of(productId));
        product.updateInventory(remains);
        repository.save(product);
        logger.info("Product inventory updated due to inventory change.");
    }
}
