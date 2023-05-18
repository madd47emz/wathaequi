package com.example.msnotification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsNotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsNotificationApplication.class, args);
    }

}
