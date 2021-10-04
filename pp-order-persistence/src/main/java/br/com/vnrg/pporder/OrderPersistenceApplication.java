package br.com.vnrg.pporder;

import br.com.vnrg.pporder.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@RequiredArgsConstructor
@ComponentScan("br.com.vnrg")
@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {Order.class})
public class OrderPersistenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderPersistenceApplication.class, args);
    }


}