package com.example.kafka.demo.service.consumer;

import com.acme.avro.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {

    @Value("${spring.kafka.producer.topic.user}")
    private  String TOPIC;

    @KafkaListener(topics = "USER_TOPIC", groupId = "group_id")
    public void consume(ConsumerRecord<String, User> record) {
        System.out.println("Consumed message -> %s"+ record.value());
    }
}
