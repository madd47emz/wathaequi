package com.example.msdemandev3.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Demande ")
@Data @AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String Nom;

    @Temporal(TemporalType.TIMESTAMP)
    private Date DateDeCreation;

    private String etats;


    //@OneToMany(mappedBy = "demande", cascade = CascadeType.ALL, orphanRemoval = true)

    private Long idDocuments;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "utilisateur_id")

    private String idUtilisateur;

    private String idAgent;



}