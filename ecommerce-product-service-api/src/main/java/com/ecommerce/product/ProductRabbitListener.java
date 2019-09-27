package com.ecommerce.product;

import com.ecommerce.inventory.sdk.event.inventory.InventoryChangedEvent;
import com.ecommerce.product.product.ProductEventHandler;
import com.ecommerce.spring.common.event.messaging.rabbit.EcommerceRabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

@Component
@EcommerceRabbitListener
public class ProductRabbitListener {
    private ProductEventHandler eventHandler;

    public ProductRabbitListener(ProductEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }


    @RabbitHandler
    public void on(InventoryChangedEvent event) {
        eventHandler.updateProductInventory(event);
    }

}
