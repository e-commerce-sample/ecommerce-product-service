package com.ecommerce.product;

import com.ecommerce.inventory.sdk.event.inventory.InventoryChangedEvent;
import com.ecommerce.spring.common.event.messaging.rabbit.EcommerceRabbitProperties;
import com.google.common.collect.ImmutableMap;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.core.Binding.DestinationType.QUEUE;

@Configuration
public class ProductRabbitmqConfig {

    private EcommerceRabbitProperties properties;

    public ProductRabbitmqConfig(EcommerceRabbitProperties properties) {
        this.properties = properties;
    }


    //Inventory服务的"发送方Exchange"，通常不应该在消费方配置发送方的Exchange，这里只是作demo用
    @Bean
    public TopicExchange inventoryPublishExchange() {
        return new TopicExchange("inventory-publish-x", true, false, ImmutableMap.of("alternate-exchange", "inventory-publish-dlx"));
    }


    //接收inventory上下文的InventoryChangedEvent事件，用于实时更新product对应的库存量
    @Bean
    public Binding bindToInventoryChanged() {
        return new Binding(properties.getReceiveQ(), QUEUE, "inventory-publish-x", InventoryChangedEvent.class.getName(), null);
    }


}
