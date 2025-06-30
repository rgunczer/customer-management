package com.ea.customer_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootApplication
public class CustomerApiApplication implements ApplicationListener<ApplicationReadyEvent> {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApiApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Spring Boot: CustomerApiApplication [READY-2-SERVE]");
    }

}
