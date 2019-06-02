package com.zy.demo.rabbitmq.consumer.listener;

import com.zy.demo.rabbitmq.consumer.constans.QueueConstants;
import com.zy.demo.rabbitmq.consumer.entity.DemoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 消费者
 * @author zy
 * @date 2019-6-1 22:14
 */
@Component
@Slf4j
public class MessageConsumer {

    /**
     * RabbitListener详细用法 <br>see ->{@link "https://docs.spring.io/spring-amqp/docs/2.1.6.RELEASE/reference/html/#async-annotation-driven"}
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = QueueConstants.MESSAGE_QUEUE_NAME_STR,durable="true"),
            exchange = @Exchange(type = ExchangeTypes.TOPIC, value = QueueConstants.MESSAGE_EXCHANGE_TOPIC),
            key = QueueConstants.MESSAGE_BINDING_KEY_STR))
    public void receiveSimpleMessage(String message){
        log.info("接收到消息 -> {}",message);
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = QueueConstants.MESSAGE_QUEUE_NAME_OBJ,durable="true"),
            exchange = @Exchange(type = ExchangeTypes.TOPIC, value = QueueConstants.MESSAGE_EXCHANGE_TOPIC),
            key = QueueConstants.MESSAGE_BINDING_KEY_OBJ))
    public void receiveObjMessage(DemoEntity message){
        log.info("接收到消息 -> {}",message);
    }

}
