package br.com.vnrg.pporder.web;

import br.com.vnrg.pporder.producer.OrderProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderProducer producer;

    @MessageMapping("/order")
    @SendTo("/order")
    public void create(@Payload String request) {
        this.producer.create(request);
    }

}
