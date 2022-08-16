package com.example.kafka.demo.service;

import com.acme.avro.Sku;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class SkuAvroProducer {

    private static KafkaProducer kafkaProducer;

    public static KafkaProducer<String, Sku> getProducer() {
        if(kafkaProducer == null) {
            final Properties props = new Properties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            props.put(ProducerConfig.ACKS_CONFIG, "all");
            props.put(ProducerConfig.RETRIES_CONFIG, 0);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
            props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

            kafkaProducer = new KafkaProducer<String, Sku>(props);
        }
        return kafkaProducer;
    }


    public void produceSku(com.example.kafka.demo.model.Sku inputSku) {

        Sku sku = Sku.newBuilder()
                .setSkuNumber(inputSku.skuNumber)
                .setStyleNumber(inputSku.styleNumber)
                .build();

        ProducerRecord<String, Sku> producerRecord = new ProducerRecord<>(
                "SKU_AVRO", sku
        );

        getProducer().send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if(exception == null) {
                    System.out.println("Success");
                    System.out.println(metadata.toString());
                } else {
                    exception.printStackTrace();
                }
            }
        });

        kafkaProducer.flush();
        kafkaProducer.close();
    }
}
