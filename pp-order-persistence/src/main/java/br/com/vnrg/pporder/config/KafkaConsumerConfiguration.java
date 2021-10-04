package br.com.vnrg.pporder.config;

import br.com.vnrg.pporder.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Slf4j
@Configuration
public class KafkaConsumerConfiguration {

    @Value("${spring.kafka.bootstrap-servers-id}")
    private transient String bootstrapServers;

    @Value("${spring.kafka.consumer.max-poll-records}")
    private transient int maxPollRecords;

    @Value("${spring.kafka.consumer.concurrency}")
    private transient int concurrency;

    @Bean
    public ConsumerFactory<String, Order> consumerFactory() {
        JsonDeserializer<Order> deserializer = new JsonDeserializer<>(Order.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return new DefaultKafkaConsumerFactory<>(Map.of(
                BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers,
                GROUP_ID_CONFIG, "order-registry",
                ENABLE_AUTO_COMMIT_CONFIG, false,
                SESSION_TIMEOUT_MS_CONFIG, 60000,
                KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                VALUE_DESERIALIZER_CLASS_CONFIG, deserializer,
                MAX_POLL_RECORDS_CONFIG, this.maxPollRecords,
                AUTO_OFFSET_RESET_CONFIG, "earliest"), new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Order> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Order> factory = new ConcurrentKafkaListenerContainerFactory<String, Order>();
        factory.setConsumerFactory(this.consumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.setErrorHandler(
                (thrownException, data) -> log.error("thrownException: {}, data: {}", thrownException, data)
        );
        factory.setConcurrency(this.concurrency);
        factory.setBatchListener(false); // true
        return factory;
    }

}
