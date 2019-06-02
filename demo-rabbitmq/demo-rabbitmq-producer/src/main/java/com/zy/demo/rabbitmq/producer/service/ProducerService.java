package com.zy.demo.rabbitmq.producer.service;

import com.zy.demo.rabbitmq.producer.entity.DemoEntity;

/**
 * @author zy
 * @date 2019-6-1 21:47
 */
public interface ProducerService {

    /**
     * 发送简单消息
     * @param message
     */
    void sendSimpleMessage(String message);

    /**
     * 发送对象消息
     * @param obj
     */
    void sendObjectMessage(DemoEntity obj);
}
