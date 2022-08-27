package com.example.kafka.demo.service.consumer;

import com.acme.avro.Sku;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Properties;

@Service
public class SkuAvroConsumer {

    private static KafkaConsumer<String, Sku> kafkaConsumer;

    public static KafkaConsumer<String, Sku> getConsumer() {
//        if(kafkaProducer == null) {
            final Properties props = new Properties();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "CONSUMER_1");
            props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
            props.put("schema.registry.url", "http://localhost:8081");
            props.put("specific.avro.reader", "true");

            kafkaConsumer = new KafkaConsumer<String, Sku>(props);
            String topic = "SKU_AVRO";
            kafkaConsumer.subscribe(Arrays.asList(topic));
//        }
        return kafkaConsumer;
    }


    public void consumeSku() {

        System.out.println("HOLAAAAAAAAAAAAAAAAAAAAAAAA-1");


        kafkaConsumer = getConsumer();
        System.out.println("HOLAAAAAAAAAAAAAAAAAAAAAAAA-2");

        try {
            while (true) {
                ConsumerRecords<String, Sku> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, Sku> record : records) {
                    System.out.println("HOLAAAAAAAAAAAAAAAAAAAAAAAA");
                    System.out.printf("offset = %d, key = %s, value = %s \n", record.offset(), record.key(), record.value());
                }
            }
        } finally {
            kafkaConsumer.close();
        }
    }
}
