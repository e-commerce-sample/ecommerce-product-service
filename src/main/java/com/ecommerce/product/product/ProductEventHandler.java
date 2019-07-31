package com.ecommerce.product.product;

import com.ecommerce.common.event.inventory.InventoryChangedEvent;
import com.ecommerce.common.logging.AutoNamingLoggerFactory;
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
    public void updateProductInventory(InventoryChangedEvent event) {
        Product product = repository.byId(of(event.getProductId()));
        product.updateInventory(event.getRemains());
        repository.save(product);
        logger.info("Product[{}] inventory updated to {} due to inventory change.", event.getProductId(), product.getInventory());
    }
}
