package com.example.auth.dao;



import com.example.auth.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface CitizenDao extends JpaRepository<Citizen,Long> {
    List<Citizen> findCitizensByGender(String gender);
    Citizen findCitizensByNin(String nin);


}
