package com.example.msparticipationcitoyen;

import com.example.msparticipationcitoyen.entities.Publication;
import com.example.msparticipationcitoyen.entities.Reply;
import com.example.msparticipationcitoyen.entities.TypePublication;
import com.example.msparticipationcitoyen.repositories.PublicationRepository;
import com.example.msparticipationcitoyen.repositories.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class MsParticipationCitoyenApplication implements CommandLineRunner {
    @Autowired
    PublicationRepository publicationRepository;


    @Autowired
    RepositoryRestConfiguration repositoryRestConfiguration;

    @Autowired
    ReplyRepository replyRepository;
    public static void main(String[] args) {
        SpringApplication.run(MsParticipationCitoyenApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {



    }
}
