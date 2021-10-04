package br.com.vnrg.pporder.producer;

import br.com.vnrg.pporder.api.v1.request.OrderRequest;
import br.com.vnrg.pporder.model.Order;
import br.com.vnrg.pporder.util.HashGenerator;
import br.com.vnrg.pporder.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderProducer {

    private final KafkaTemplate<String, Order> template;

    @Value("${spring.kafka.topic-order}")
    public String topic;

    @Transactional
    public void create(final OrderRequest request, final String token) {
        var customer = JWTUtil.getClaim(token, "user");
        var key = HashGenerator.get();
        var order = Order.builder()
                .amount(request.getAmount())
                .asset(request.getAsset())
                .customerId(customer)
                .build();

        ProducerRecord<String, Order> producerRecord = new ProducerRecord<>(this.topic, key, order);
        producerRecord.headers().add("event-cli", "api".getBytes());
        template.send(producerRecord);

        ProducerRecord<String, Order> producerRecord2 = new ProducerRecord<>(this.topic, key, order);
        template.send(producerRecord2);

        log.info("Send order {} , key {}", order, key);
    }

}
