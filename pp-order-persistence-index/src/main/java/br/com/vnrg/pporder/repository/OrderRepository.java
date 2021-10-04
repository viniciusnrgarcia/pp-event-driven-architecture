package br.com.vnrg.pporder.repository;

import br.com.vnrg.pporder.model.OrderIndex;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class OrderRepository {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public void index(final OrderIndex order) {
        try {
            this.elasticsearchRestTemplate.save(order);
        } catch (Exception e) {
            log.error("Error processing order {} {} ", order, e);
            throw new RuntimeException();
        }
    }
}
