package com.example.testing.repositories;


import com.example.testing.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeRepository extends JpaRepository<Demande,Long> {
    //List<Demande> findByDocuments_Id(Long documentId);
}
