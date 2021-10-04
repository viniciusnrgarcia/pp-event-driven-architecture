package br.com.vnrg.pporder.service;

import br.com.vnrg.pporder.model.Order;
import br.com.vnrg.pporder.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderService {

    private final OrderRepository repository;

    public void save(final Order order) {
        try {
            // mongo
            this.repository.save(order);
        } catch (Exception e) {
            log.error("Error processing order {} {} ", order, e);
            throw new RuntimeException();
        }
    }
}
