package com.example.msdocument.Entity;

import com.example.msdocument.Model.Demande;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String Type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date DateDeCreation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date DateExpiration;

    private String File;

   @Transient
   private Collection<Demande> demandes;
}
