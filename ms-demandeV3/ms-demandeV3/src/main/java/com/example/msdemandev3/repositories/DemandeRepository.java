package com.example.msdemandev3.repositories;

import com.example.msdemandev3.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande,Long> {
    //List<Demande> findByDocuments_Id(Long documentId);

    List<Demande> findDemandeByIdUtilisateur(String IdUtilisateur);
    List<Demande> findDemandeByIdAgent(String IdAgent);

    List<Demande> findDemandesByEtats(String Etats);

}