package br.com.vnrg.pporderstream.config;

import br.com.vnrg.pporderstream.model.Order;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Configuration
public class KafkaProducerConfiguration {

    @Value("${spring.kafka.bootstrap-servers-id}")
    private transient String bootstrapServers;

    @Bean
    public ProducerFactory<String, Order> producerFactory() {
        return new DefaultKafkaProducerFactory<>(
                Map.of(BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers,
                        RETRIES_CONFIG, 5,
                        BUFFER_MEMORY_CONFIG, 33554432,
                        KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                        VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class,
                        ACKS_CONFIG, "all",
                        MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5",
                        LINGER_MS_CONFIG, 30,
                        ENABLE_IDEMPOTENCE_CONFIG, "true"
                )
        );
    }

    @Bean
    public KafkaTemplate<String, Order> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
