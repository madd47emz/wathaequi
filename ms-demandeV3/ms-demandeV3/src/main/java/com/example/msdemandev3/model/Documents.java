package com.example.msdemandev3.model;

import com.example.msdemandev3.entity.Demande;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "demande_id")
    private Demande demande;
}
