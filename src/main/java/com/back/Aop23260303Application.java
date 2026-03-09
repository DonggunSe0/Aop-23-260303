package com.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Aop23260303Application {

    public static void main(String[] args) {
        SpringApplication.run(Aop23260303Application.class, args);
    }

}
