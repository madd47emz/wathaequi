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
        Publication p1= new Publication(null, TypePublication.Signalement,"Content P1",null,"Adr1","sabra","tlemcen",new Date("01/01/2022"),null,"anes the agent","anesagent");
        Publication p2= new Publication(null, TypePublication.Signalement,"Content P2",null,"Adr2","sabra","tlemcen",new Date("01/01/2022"),null,"anes the agent","anesagent");

        Publication p3= new Publication(null, TypePublication.Avis,"Content P3",null,"Adr3","sabra","tlemcen",new Date("01/01/2022"),null,"anes the agent","anesagent");
        Publication p4= new Publication(null, TypePublication.Avis,"Content P4",null,"Adr4","sabra","tlemcen",new Date("01/01/2022"),null,"anes the agent","anesagent");


        Reply r1= new Reply(null,new Date("01/01/2022"),null,p1,"replyyyy1");
        Reply r2= new Reply(null,new Date("01/01/2022"),null,p2,"replyyyy1");
        publicationRepository.save(p1);
        publicationRepository.save(p2);
        publicationRepository.save(p3);
        publicationRepository.save(p4);
        replyRepository.save(r1);
        replyRepository.save(r2);
    }
}
