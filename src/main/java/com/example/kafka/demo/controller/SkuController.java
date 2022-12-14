package com.example.kafka.demo.controller;

import com.example.kafka.demo.model.Sku;
import com.example.kafka.demo.service.consumer.SkuAvroConsumer;
import com.example.kafka.demo.service.producer.SkuAvroProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class SkuController {

    @Autowired
    SkuAvroProducer skuProducer;

    @Autowired
    SkuAvroConsumer skuAvroConsumer;

    @GetMapping(value = "/skus")
    public String getSkus() {
        return "SKU's";
    }

    @PostMapping(value = "/sku")
    public void publishSku(@RequestBody Sku sku) {
//        System.out.println("*****");
//        System.out.println(sku.skuNumber);
//        System.out.println(sku.styleNumber);
//        System.out.println("*****");

        skuProducer.produceSku(sku);
        skuAvroConsumer.consumeSku();
    }
}
