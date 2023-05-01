package com.example.msdocument.Repository;

import com.example.msdocument.Entity.Documents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocRep extends JpaRepository<Documents,Long> {
}