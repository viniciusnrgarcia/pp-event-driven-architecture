package br.com.vnrg.pporder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.vnrg")
public class OrderWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderWebsocketApplication.class, args);
    }
}
