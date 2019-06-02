package com.zy.demo.rabbitmq.producer.service.impl;

import com.zy.demo.rabbitmq.producer.constants.QueueConstants;
import com.zy.demo.rabbitmq.producer.entity.DemoEntity;
import com.zy.demo.rabbitmq.producer.service.ProducerService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zy
 * @date 2019-6-1 21:55
 */
@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendSimpleMessage(String message) {
        amqpTemplate.convertAndSend(QueueConstants.MESSAGE_EXCHANGE_TOPIC,QueueConstants.MESSAGE_ROUTE_KEY_STR,message);
    }

    @Override
    public void sendObjectMessage(DemoEntity obj) {
        amqpTemplate.convertAndSend(QueueConstants.MESSAGE_EXCHANGE_TOPIC,QueueConstants.MESSAGE_ROUTE_KEY_OBJ,obj);
    }
}
