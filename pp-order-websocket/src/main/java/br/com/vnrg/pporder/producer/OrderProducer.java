package br.com.vnrg.pporder.producer;

import br.com.vnrg.pporder.model.Order;
import br.com.vnrg.pporder.util.UniqueID;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderProducer {

    @Value("${spring.kafka.topic-order}")
    public String topic;

    private final KafkaTemplate<String, Order> template;

    public void create(final String requestJson) {
        var order = new Gson().fromJson(requestJson, Order.class);
        ProducerRecord<String, Order> producerRecord = new ProducerRecord<>(this.topic, order);
        producerRecord.headers().add("event-id", UniqueID.getUniqueIdBytes());
        producerRecord.headers().add("event-cli", "api".getBytes());
        producerRecord.headers().add("api-token", order.getToken().getBytes());
        template.send(producerRecord);
        log.info("Order send topic: {}", order);
    }

}
