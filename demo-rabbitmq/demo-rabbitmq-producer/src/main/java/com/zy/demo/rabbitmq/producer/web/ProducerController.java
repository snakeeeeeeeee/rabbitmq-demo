package com.zy.demo.rabbitmq.producer.web;

import com.zy.demo.rabbitmq.producer.entity.DemoEntity;
import com.zy.demo.rabbitmq.producer.service.ProducerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zy
 * @date 2019-6-1 22:59
 */
@RestController
@Slf4j
@AllArgsConstructor
public class ProducerController {
    private final ProducerService producerService;

    @GetMapping("/sendSimpleMessage")
    public void sendSimpleMessage(String message){
        producerService.sendSimpleMessage(message);
    }

    @GetMapping("/sendObjMessage")
    @ResponseBody
    public void sendObjMessage(DemoEntity message){
        List<String> list = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        list.add("list data 1");
        list.add("list data 2");
        if(ObjectUtils.isEmpty(message)){
            message = new DemoEntity();
            message.setName("Test Name");
            message.setAge(18);
        }
        message.setList(list);
        message.setExt(map);
        producerService.sendObjectMessage(message);
    }
}
