package com.ecommerce.product;

import com.ecommerce.common.event.consume.EcommerceRabbitListener;
import com.ecommerce.common.event.inventory.InventoryChangedEvent;
import com.ecommerce.product.product.ProductEventHandler;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Component;

@Component
@EcommerceRabbitListener
public class ProductRabbitListener {
    private ProductEventHandler eventHandler;

    public ProductRabbitListener(ProductEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }


    @NewSpan("Update inventory according to inventory")
    @RabbitHandler
    public void on(InventoryChangedEvent event) {
        eventHandler.updateProductInventory(event);
    }

}
