package com.example.msparticipationcitoyen.entities;

import com.example.msparticipationcitoyen.model.CitizenAuth;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String Content;

    private String Picture;

    private Date datePublication;

    @JsonIgnore
    @OneToMany(mappedBy = "publication")
    private Collection<Reply> Reponces;

//    private Long idCommune;

    //**********************User
    /*@Transient
    private CitizenAuth citizen;*/
    @ManyToOne
    private Citizen citizen;



}
