package br.com.vnrg.pporderstream.service;

import br.com.vnrg.pporderstream.model.Customer;
import br.com.vnrg.pporderstream.model.Order;
import br.com.vnrg.pporderstream.producer.OrderPersistenceProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.header.Headers;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final RedisTemplate<String, String> template;
    private final OrderPersistenceProducer producer;

    public void process(final Order order, final String key, final Headers headers) {
        try {
            var customer = this.getCustomer(order.getCustomerId());
            order.setValue(customer.getPricing().getValue());
            order.setCustomerId(customer.getId());
            order.setPricingType(customer.getPricing().getPricingType());
            this.producer.send(order, key, headers);
            log.info("processed order {} , key {} ", order, key);

        } catch (Exception e) {
            log.error("Error processing order {}, key {}, {} ", order, key, e);
            // throws exception to interceptor send DLQ
            throw new RuntimeException();
        }
    }

    @SneakyThrows
    private Customer getCustomer(String customerId) {
        var item = this.getPricing(customerId);
        return mapper.readValue(item, Customer.class);
    }

    private String getPricing(final String customer) {
        return template.opsForValue().get(customer);
    }
}
