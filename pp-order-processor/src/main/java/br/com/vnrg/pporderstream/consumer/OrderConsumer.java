package br.com.vnrg.pporderstream.consumer;

import br.com.vnrg.pporderstream.model.Order;
import br.com.vnrg.pporderstream.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class OrderConsumer {
    private final OrderService service;

    @KafkaListener(topics = {"${spring.kafka.consumer.topic-order}"}, groupId = "${spring.kafka.consumer.group-id}",
            id = "${spring.kafka.consumer.listener-id}", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, Order> record, Acknowledgment ack) {
        this.service.process(record.value(), record.key(), record.headers());
        ack.acknowledge();
    }

}