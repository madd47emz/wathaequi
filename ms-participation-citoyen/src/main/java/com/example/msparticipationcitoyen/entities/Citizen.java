package com.example.msparticipationcitoyen.entities;

import com.example.msparticipationcitoyen.model.CitizenAuth;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCitizen;

    private String fullNameLat;
    @JsonIgnore
    @OneToMany(mappedBy = "citizen")
    List<Publication> publications;
    @Transient
    CitizenAuth citizenAuth;

    private String commune;
}
