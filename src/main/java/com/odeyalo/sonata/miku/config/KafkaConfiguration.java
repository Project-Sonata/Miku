package com.odeyalo.sonata.miku.config;

import com.odeyalo.sonata.suite.brokers.events.SonataEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Bean
    public ReceiverOptions<String, SonataEvent> albumEventWarehouseKafkaReceiver(KafkaProperties props) {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, props.getBootstrapServers());
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, props.getConsumer().getGroupId());
        consumerProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return ReceiverOptions.<String, SonataEvent>create(consumerProps)
                .subscription(Collections.singleton("albums-event-warehouse"));
    }

    @Bean
    public ReactiveKafkaConsumerTemplate<String, SonataEvent> reactiveKafkaConsumerTemplate(ReceiverOptions<String, SonataEvent> kafkaReceiverOptions) {
        return new ReactiveKafkaConsumerTemplate<>(kafkaReceiverOptions);
    }
}
