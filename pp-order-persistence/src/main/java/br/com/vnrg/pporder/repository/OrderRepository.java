package br.com.vnrg.pporder.repository;

import br.com.vnrg.pporder.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class OrderRepository {

    private final MongoTemplate mongoTemplate;

    public void save(final Order order) {
        try {
            var existingOrder = this.mongoTemplate.findOne(Query.query(Criteria.where("eventId").is(order.getEventId())), Order.class);
            if (existingOrder == null) {
                this.mongoTemplate.save(order);
            }
        } catch (Exception e) {
            log.error("Error processing order {} {} ", order, e);
            throw new RuntimeException();
        }
    }
}
