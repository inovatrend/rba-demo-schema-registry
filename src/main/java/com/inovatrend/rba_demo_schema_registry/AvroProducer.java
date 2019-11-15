package com.inovatrend.rba_demo_schema_registry;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.inovatrend.Person;

import java.util.Properties;

public class AvroProducer {

    public static void main(String[] args) {

        Properties config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        config.put("schema.registry.url", "http://localhost:8081");

        KafkaProducer<String, Person> producer = new KafkaProducer<>(config);

        Person person = new Person("marko", "Zagreb", 29, "marko@example.com", "");
        producer.send(new ProducerRecord<>("person-avro-topic",person ));

        System.out.println("Done");

        producer.flush();
        producer.close();

    }
}
