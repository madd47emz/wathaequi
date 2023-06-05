package com.example.msdemandev3;

import com.example.msdemandev3.entity.Demande;
import com.example.msdemandev3.repositories.DemandeRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsDemandeV3Application implements CommandLineRunner {

    @Autowired
    DemandeRepository demandeRepository;

    public static void main(String[] args) {
        SpringApplication.run(MsDemandeV3Application.class, args);

    }


    @Override
    public void run(String... args) throws Exception {

    }
}
