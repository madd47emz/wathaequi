package com.example.citizens;

import com.example.citizens.model.Citizen;
import com.example.citizens.repository.CitizenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class CitizensApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CitizensApplication.class, args);
    }
         @Autowired
         CitizenRepo citizenRepo;

    @Override
    public void run(String... args) throws Exception {
        Citizen citizen= new Citizen(1L,"fer","2735265278526");
        citizenRepo.save(citizen);
        citizenRepo.findAll();
    }
}
