package com.example.msdemandev3.entity;

import com.example.msdemandev3.model.Citizen;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;


@Entity
@Table(name = "Demande ")
@Data @AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String DemandeNum;

    @Temporal(TemporalType.TIMESTAMP)
    private Date DateDeCreation;

    private String etats;


    //@OneToMany(mappedBy = "demande", cascade = CascadeType.ALL, orphanRemoval = true)

    private Long idDocuments;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "utilisateur_id")

    private String commune;

    private String wilaya;
    private String idUtilisateur;

    private String idAgent;

    @Transient
    private Collection<Citizen> citizens;



}
