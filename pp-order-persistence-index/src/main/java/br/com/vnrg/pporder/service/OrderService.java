package br.com.vnrg.pporder.service;

import br.com.vnrg.pporder.model.Order;
import br.com.vnrg.pporder.model.OrderIndex;
import br.com.vnrg.pporder.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderService {

    private final OrderRepository repository;

    public void save(final Order order) {
        try {
            OrderIndex index = new OrderIndex();
            index.setAsset(order.getAsset());
            index.setValue(order.getValue());
            index.setAmout(order.getAmount());
            index.setPricingType(order.getPricingType());
            index.setCustomerId(order.getCustomerId());
            index.setTimestamp(Instant.now());
            index.setEventId(order.getEventId());
            this.repository.index(index);
        } catch (Exception e) {
            log.error("Error processing order {} {} ", order, e);
            throw new RuntimeException();
        }
    }
}
