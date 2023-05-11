package com.example.msdemandev3.repositories;

import com.example.msdemandev3.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepository extends JpaRepository<Demande,Long> {
    //List<Demande> findByDocuments_Id(Long documentId);
}
