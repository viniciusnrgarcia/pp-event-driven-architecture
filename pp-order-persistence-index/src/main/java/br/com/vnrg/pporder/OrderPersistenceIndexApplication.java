package br.com.vnrg.pporder;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@RequiredArgsConstructor
@ComponentScan("br.com.vnrg")
@SpringBootApplication
public class OrderPersistenceIndexApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderPersistenceIndexApplication.class, args);
    }


}