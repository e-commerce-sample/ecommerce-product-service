package com.ecommerce.product;

import com.ecommerce.common.event.consume.EcommerceRabbitListener;
import com.ecommerce.common.event.inventory.InventoryChangedEvent;
import com.ecommerce.common.logging.AutoNamingLoggerFactory;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

@Component
@EcommerceRabbitListener
public class ProductRabbitListener {
    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    private ProductEventHandler eventHandler;

    public ProductRabbitListener(ProductEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }


    @RabbitHandler
    public void on(InventoryChangedEvent event) {
        eventHandler.updateProductInventory(event.getProductId(), event.getRemains());
    }

}
