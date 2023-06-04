package com.example.msparticipationcitoyen.repositories;

import com.example.msparticipationcitoyen.entities.Citizen;
import com.example.msparticipationcitoyen.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Long> {

    List<Publication> findPublicationsByCitizenIdCitizen(Long id);
    List<Publication> findPublicationsByCitizenCommune(String commune);

    List<Publication> findPublicationsByTypePublication(String typePublication);
    List<Publication> findPublicationsByTypePublicationAndCitizenIdCitizen(String typePublication, String idCitizen);


}
