package com.ecommerce.product;

import com.ecommerce.common.event.EcommerceRabbitProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.core.Binding.DestinationType.QUEUE;

@Configuration
public class ProductRabbitmqConfig {

    private EcommerceRabbitProperties properties;

    public ProductRabbitmqConfig(EcommerceRabbitProperties properties) {
        this.properties = properties;
    }

    //将product上下文的"接收方queue"绑定到inventory上下文的"发送方exchange"，用于实时更新product对应的库存量
    @Bean
    public Binding bindToInventory() {
        return new Binding(properties.getReceiveQ(), QUEUE, "inventory-publish-x", "inventory.changed", null);
    }


}
