package com.example.msparticipationcitoyen.model;

import com.example.msparticipationcitoyen.entities.Citizen;
import com.example.msparticipationcitoyen.entities.Reply;
import com.example.msparticipationcitoyen.entities.TypePublication;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data

public class publicationRequest {

    @Enumerated(EnumType.STRING)
    private TypePublication typePublication;

    private String Content;

    private Date datePublication;

    private String idUser; //NIN

    private String address;
    private String commune;






}
