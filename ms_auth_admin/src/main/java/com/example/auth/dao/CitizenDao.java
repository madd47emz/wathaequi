package com.example.auth.dao;



import com.example.auth.entities.Citizen;
import com.example.auth.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource
public interface CitizenDao extends JpaRepository<Citizen,Long> {
    List<Citizen> findCitizensByGender(Gender gender );
    Citizen findCitizensByNin(@Param("nin")String nin);


}
