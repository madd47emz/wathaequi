package com.example.msgestiondemandedocuments_v1.ms;

import com.example.msgestiondemandedocuments_v1.dto.DemandeRequest;
import com.example.msgestiondemandedocuments_v1.model.Demande;
import com.example.msgestiondemandedocuments_v1.repositories.DemandeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ServiceDemande {
    private DemandeRepository demandeRepository;
    public void demander(DemandeRequest demandeRequest){

        Demande demande=new Demande();
        demande.setDemandeNum(UUID.randomUUID().toString());
        demande.setDateDeCreation(new Date());
        demande.setEtats(demande.getEtats());


        demandeRepository.save(demande);

    }
}
