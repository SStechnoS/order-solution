package com.ordersolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@SpringBootApplication
@EnableJdbcAuditing
public class OrderSolutionApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderSolutionApplication.class, args);
    }

}
