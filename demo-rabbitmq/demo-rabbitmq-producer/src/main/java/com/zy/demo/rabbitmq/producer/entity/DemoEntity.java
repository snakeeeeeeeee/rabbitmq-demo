package com.zy.demo.rabbitmq.producer.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author zy
 * @date 2019-6-1 23:19
 */
@Data
public class DemoEntity{

    private String name;

    private Integer age;

    private List<String> list;

    private Map<String,String> ext;
}
