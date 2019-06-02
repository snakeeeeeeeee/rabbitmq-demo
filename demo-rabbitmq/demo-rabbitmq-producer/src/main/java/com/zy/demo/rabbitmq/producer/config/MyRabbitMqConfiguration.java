package com.zy.demo.rabbitmq.producer.config;

import com.zy.demo.rabbitmq.producer.constants.QueueConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq 配置
 * @author zy
 * @date 2019-6-1 21:12
 */
@Configuration
public class MyRabbitMqConfiguration {

    /**
     * 声明队列
     * @return
     */
    @Bean
    public Queue stringQueue(){
        return QueueBuilder.durable(QueueConstants.MESSAGE_QUEUE_NAME_STR).build();
    }

    /**
     * 复杂对象队列
     * @return
     */
    @Bean
    public Queue objectQueue(){
        return QueueBuilder.durable(QueueConstants.MESSAGE_QUEUE_NAME_OBJ).build();
    }

    /**
     * topic 类型交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return (TopicExchange)ExchangeBuilder.topicExchange(QueueConstants.MESSAGE_EXCHANGE_TOPIC).
                durable(true).build();

    }

    /**
     * 字符串队列
     * @param stringQueue
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindingStringExchangeMessage(@Qualifier("stringQueue") Queue stringQueue, TopicExchange exchange){
        return BindingBuilder.bind(stringQueue).to(exchange).with(QueueConstants.MESSAGE_QUEUE_NAME_STR);
    }

    /**
     * 对象队列
     * @param objectQueue
     * @param exchange
     * @return
     */
    @Bean
    public Binding bindingObjectExchangeMessage(@Qualifier("objectQueue") Queue objectQueue, TopicExchange exchange){
        return BindingBuilder.bind(objectQueue).to(exchange).with(QueueConstants.MESSAGE_QUEUE_NAME_OBJ);
    }

    /**
     * 使用jackson message converter 来序列化对象
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    /**
     * 声明jackson message converter
     * @return
     */
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
