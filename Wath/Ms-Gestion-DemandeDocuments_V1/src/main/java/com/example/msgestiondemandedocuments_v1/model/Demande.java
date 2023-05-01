package com.example.msgestiondemandedocuments_v1.model;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private String Etats;


    //@OneToMany(mappedBy = "demande", cascade = CascadeType.ALL, orphanRemoval = true)

    private Long idDocuments;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "utilisateur_id")

    private Long idUtilisateur;



}
