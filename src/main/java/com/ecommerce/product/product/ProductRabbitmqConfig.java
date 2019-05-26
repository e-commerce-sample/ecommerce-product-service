package com.ecommerce.product.product;

import com.google.common.collect.ImmutableMap;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.google.common.collect.ImmutableMap.of;

@Configuration
public class ProductRabbitmqConfig {

    public static final String PRODUCT_PUBLISH_EXCHANGE = "product";
    public static final String PRODUCT_PUBLISH_DLX = "product-publish-dlx";
    public static final String PRODUCT_PUBLISH_DLQ = "product-publish-dlq";

    public static final String PRODUCT_MANUAL_DLQ_RETRY_EXCHANGE = "product-manual-dlq-retry-x";
    public static final String PRODUCT_RECEIVE_QUEUE = "product-receive-q";
    public static final String PRODUCT_RECEIVE_DLX = "product-receive-dlx";
    public static final String PRODUCT_RECEIVE_DLQ = "product-receive-dlq";


    //product上下文的"发送方exchange"，所有发自product上下文的消息都发自该exchange
    @Bean
    public TopicExchange productPublishExchange() {
        return new TopicExchange(PRODUCT_PUBLISH_EXCHANGE, true, false, of("alternate-exchange", PRODUCT_PUBLISH_DLX));
    }

    //product上下文的"发送方DLX"，消息发送失败时传到该DLX
    @Bean
    public TopicExchange productPublishDLX() {
        return new TopicExchange(PRODUCT_PUBLISH_DLX, true, false, null);
    }

    //product上下文的"发送方DLQ"，所有发到"发送DLX"的消息都将路由到该DLQ
    @Bean
    public Queue productPublishDLQ() {
        return new Queue(PRODUCT_PUBLISH_DLQ, true, false, false, of("x-queue-mode", "lazy"));
    }

    //将product上下文的"发送方DLQ"绑定到"发送方DLX"
    @Bean
    public Binding publishDlqBinding() {
        return BindingBuilder.bind(productPublishDLQ()).to(productPublishDLX()).with("#");
    }

    //product上下文中接收到的所有消息都发送到该"接收方queue"
    @Bean
    public Queue productReceiveQueue() {
        ImmutableMap<String, Object> args = of(
                "x-dead-letter-exchange",
                PRODUCT_RECEIVE_DLX,
                "x-overflow",
                "drop-head",
                "x-max-length",
                300000,
                "x-message-ttl",
                24 * 60 * 60 * 1000);
        return new Queue(PRODUCT_RECEIVE_QUEUE, true, false, false, args);
    }

    //product上下文的"手动重试exchange"，用于手动将"接收方DLQ"中的消息发自该DLX进行重试
    @Bean
    public TopicExchange productManualRetryExchange() {
        return new TopicExchange(PRODUCT_MANUAL_DLQ_RETRY_EXCHANGE, true, false, of("alternate-exchange", PRODUCT_RECEIVE_DLX));
    }

    //将product上下文的"接收方Queue"绑定到"手动重试exchange"
    @Bean
    public Binding recoverBinding() {
        return BindingBuilder.bind(productReceiveQueue()).to(productManualRetryExchange()).with("#");
    }


    //product上下文的"接收方DLX"，消息接收失败时传到该DLX
    @Bean
    public TopicExchange productReceiveDLX() {
        return new TopicExchange(PRODUCT_RECEIVE_DLX, true, false, null);
    }


    //product上下文的"接收方DLQ"，所有发到"接收DLX"的消息都将路由到该DLQ
    @Bean
    public Queue productReceiveDLQ() {
        return new Queue(PRODUCT_RECEIVE_DLQ, true, false, false, of("x-queue-mode", "lazy"));
    }

    //将product上下文的"接收方DLQ"绑定到"接收方DLX"
    @Bean
    public Binding receiveDlqBinding() {
        return BindingBuilder.bind(productReceiveDLQ()).to(productReceiveDLX()).with("#");
    }


}
