package com.example.msparticipationcitoyen.repositories;

import com.example.msparticipationcitoyen.entities.Citizen;
import com.example.msparticipationcitoyen.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {

}