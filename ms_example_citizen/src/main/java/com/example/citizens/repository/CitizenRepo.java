package com.example.citizens.repository;

import com.example.citizens.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepo extends JpaRepository<Citizen, Long> {
}
