package br.com.vnrg.pporderstream.producer;

import br.com.vnrg.pporderstream.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderPersistenceProducer {

    private final KafkaTemplate<String, Order> template;

    @Value("${spring.kafka.producer.topic-order-persistence}")
    private String topic;

    public void send(final Order order, String key, Headers headers) {
        try {
            ProducerRecord<String, Order> producerRecord = new ProducerRecord<>(this.topic, key, order);
            template.send(producerRecord);
            log.info("Send order {} , key {}", order, key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException();
        }
    }

}
