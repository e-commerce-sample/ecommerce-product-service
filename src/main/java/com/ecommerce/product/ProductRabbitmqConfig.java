package com.ecommerce.product;

import com.ecommerce.common.event.EcommerceRabbitProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.ecommerce.common.event.DomainEventType.INVENTORY_CHANGED;
import static org.springframework.amqp.core.Binding.DestinationType.QUEUE;

@Configuration
public class ProductRabbitmqConfig {

    private EcommerceRabbitProperties properties;

    public ProductRabbitmqConfig(EcommerceRabbitProperties properties) {
        this.properties = properties;
    }

    //接收inventory上下文的InventoryChangedEvent事件，用于实时更新product对应的库存量
    @Bean
    public Binding bindToInventoryChanged() {
        return new Binding(properties.getReceiveQ(), QUEUE, "inventory-publish-x", INVENTORY_CHANGED.toRoutingKey(), null);
    }


}
