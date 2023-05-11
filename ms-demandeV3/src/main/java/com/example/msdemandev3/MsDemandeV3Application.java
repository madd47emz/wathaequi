package com.example.msdemandev3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsDemandeV3Application {

    public static void main(String[] args) {
        SpringApplication.run(MsDemandeV3Application.class, args);
    }

}
