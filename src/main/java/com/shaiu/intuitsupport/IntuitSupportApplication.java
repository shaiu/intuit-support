package com.shaiu.intuitsupport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IntuitSupportApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntuitSupportApplication.class, args);
    }
}
