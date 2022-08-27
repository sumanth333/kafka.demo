package com.example.kafka.demo.controller;

import com.example.kafka.demo.model.User;
import com.example.kafka.demo.service.consumer.SkuAvroConsumer;
import com.example.kafka.demo.service.producer.UserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController("/user")
@RestController
public class UserController {

    @Autowired
    UserProducer userProducer;

    @Autowired
    SkuAvroConsumer skuAvroConsumer;

    @GetMapping(value = "/getAllUsers")
    public String getSkus() {
        return "SKU's";
    }

    @PostMapping(value = "/publish")
    public void publishSku(@RequestBody User user) {

        userProducer.sendMessage(user);
//        skuAvroConsumer.consumeSku();
    }
}
