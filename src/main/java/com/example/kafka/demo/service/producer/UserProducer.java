//package com.example.kafka.demo.service.producer;
//
//import com.acme.avro.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserProducer {
//
//    @Value("${spring.kafka.producer.topic.user}")
//    private String TOPIC;
//
//    private final KafkaTemplate<String, User> kafkaTemplate;
//
//    @Autowired
//    public UserProducer(KafkaTemplate<String, User> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void sendMessage(com.example.kafka.demo.model.User inputUser) {
//
//        User user = User.newBuilder()
//                        .setName(inputUser.name)
//                        .setAge(inputUser.age)
//                        .build();
//
//        this.kafkaTemplate.send(this.TOPIC, user.getName(), user);
//        System.out.println("Produced user -> %s"+ user);
//    }
//}
