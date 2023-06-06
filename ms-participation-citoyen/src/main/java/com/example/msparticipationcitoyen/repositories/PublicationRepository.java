package com.example.msparticipationcitoyen.repositories;

import com.example.msparticipationcitoyen.entities.Publication;
import com.example.msparticipationcitoyen.entities.TypePublication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PublicationRepository extends JpaRepository<Publication, Long> {


    List<Publication> findPublicationsByIdCitizen(String id);

    List<Publication> findPublicationsByCommune(String commune);

    List<Publication> findPublicationsByCommuneAndWilaya(String commune,String wilaya);




    List<Publication> findPublicationsByTypePublication(TypePublication typePublication);
    List<Publication> findPublicationsByTypePublicationAndIdCitizen(TypePublication typePublication, String idCitizen);

    List<Publication> findPublicationsByTypePublicationAndCommuneAndWilaya(TypePublication typePublication, String commune,String wilaya);


}
