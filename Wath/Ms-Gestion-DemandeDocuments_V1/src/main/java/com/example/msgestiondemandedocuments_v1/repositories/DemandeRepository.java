package com.example.msgestiondemandedocuments_v1.repositories;

import com.example.msgestiondemandedocuments_v1.model.Demande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande,Long> {
    //List<Demande> findByDocuments_Id(Long documentId);
}
