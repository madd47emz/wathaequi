package com.example.msparticipationcitoyen.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublication;

    @Enumerated(EnumType.STRING)
    private TypePublication typePublication;

    private String content;

    @Lob
    private byte[] picture;

    private String adresse;
    private String commune;

    private String wilaya;


    private Date datePublication;



    @OneToMany(mappedBy = "publication",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Reply> reponces;

    private String fullNameCitizen;

    private String idCitizen;





}
