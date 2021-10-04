package br.com.vnrg.pporderstream;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@RequiredArgsConstructor
@ComponentScan("br.com.vnrg")
@SpringBootApplication
public class OrderProcessorApplication  {

    public static void main(String[] args) {
        SpringApplication.run(OrderProcessorApplication.class, args);
    }


}