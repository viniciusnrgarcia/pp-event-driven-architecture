package br.com.vnrg.pporder.consumer;

import br.com.vnrg.pporder.model.Order;
import br.com.vnrg.pporder.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class OrderPersistenceConsumer {

    private final OrderService service;

    @KafkaListener(topics = {"${spring.kafka.consumer.topic-order}"}, groupId = "${spring.kafka.consumer.group-id}",
            id = "${spring.kafka.consumer.listener-id}", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, Order> record, Acknowledgment ack) {
        var order = record.value();
        order.setEventId(record.key());
        log.info("Message received: {} with key: {} ", order, record.key());
        this.service.save(order);
        ack.acknowledge();
    }

}