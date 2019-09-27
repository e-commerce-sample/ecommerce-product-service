package com.ecommerce.product.product;

import com.ecommerce.inventory.sdk.event.inventory.InventoryChangedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ProductEventHandler {

    private ProductRepository repository;

    public ProductEventHandler(ProductRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public void updateProductInventory(InventoryChangedEvent event) {
        Product product = repository.byId(event.getProductId());
        product.updateInventory(event.getRemains());
        repository.save(product);
        log.info("Product[{}] inventory updated to {} due to inventory change.", event.getProductId(), product.getInventory());
    }
}
