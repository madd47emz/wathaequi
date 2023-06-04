package com.example.auth.dao;

import com.example.auth.entities.Agent;
import com.example.auth.entities.Citizen;
import com.example.auth.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AgentDao extends JpaRepository<Agent,Long> {
    Boolean existsByNin(String nin);

}