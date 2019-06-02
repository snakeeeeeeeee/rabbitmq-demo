package com.zy.demo.rabbitmq.producer.constants;

/**
 * @author zy
 * @date 2019-6-1 21:13
 */
public class QueueConstants {

    //通配符交换机 topic
    public static final String MESSAGE_EXCHANGE_TOPIC = "message.exchange.topic";
    //路由消息交机 direct
    public static final String MESSAGE_EXCHANGE_DIRECT = "message.exchange.direct";
    //发布订阅交换机 fanout
    public static final String MESSAGE_EXCHANGE_FANOUT = "message.exchange.fanout";
    //消息队列名称
    public static final String MESSAGE_QUEUE_NAME_STR= "message.queue.str";
    public static final String MESSAGE_QUEUE_NAME_OBJ = "message.queue.obj";
    //消息路由键
    public static final String MESSAGE_ROUTE_KEY_STR = "message.key.str";
    public static final String MESSAGE_ROUTE_KEY_OBJ = "message.key.obj";

}
