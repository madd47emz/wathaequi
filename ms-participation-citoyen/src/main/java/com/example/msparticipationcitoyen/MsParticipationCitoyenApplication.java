package com.example.msparticipationcitoyen;

import com.example.msparticipationcitoyen.entities.Citizen;
import com.example.msparticipationcitoyen.entities.Publication;
import com.example.msparticipationcitoyen.entities.Reply;
import com.example.msparticipationcitoyen.entities.TypePublication;
import com.example.msparticipationcitoyen.repositories.CitizenRepository;
import com.example.msparticipationcitoyen.repositories.PublicationRepository;
import com.example.msparticipationcitoyen.repositories.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class MsParticipationCitoyenApplication implements CommandLineRunner {
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    CitizenRepository citizenRepository;

    @Autowired
    RepositoryRestConfiguration repositoryRestConfiguration;

    @Autowired
    ReplyRepository replyRepository;
    public static void main(String[] args) {
        SpringApplication.run(MsParticipationCitoyenApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Citizen c1= new Citizen(null,"c1",null,null,"sabra");
        Citizen c3= new Citizen(null,"c3",null,null,"sabra");
        Citizen c2= new Citizen(null,"c2",null,null,"ouledSellam");
        Publication p1= new Publication(null, TypePublication.Avis,"Content P1","Pic1",new Date("01/01/2022"),null, c1);
        Publication p2= new Publication(null, TypePublication.Avis,"Content P2","Pic2",new Date("01/01/2022"),null, c1);
        Publication p3= new Publication(null, TypePublication.Avis,"Content P3","Pic3",new Date("01/01/2022"),null,c2);
        Publication p4= new Publication(null, TypePublication.Avis,"Content P3","Pic3",new Date("01/01/2022"),null,c3);

        Reply r1 = new Reply(null, new Date("22/12/2023"),"pic1",p1,"conrep1",null,"nameeee");
        citizenRepository.save(c1);
        citizenRepository.save(c2);
        citizenRepository.save(c3);
        publicationRepository.save(p1);
        publicationRepository.save(p2);
        publicationRepository.save(p3);
        publicationRepository.save(p4);
        replyRepository.save(r1);


        repositoryRestConfiguration.exposeIdsFor(Publication.class);



    }
}
