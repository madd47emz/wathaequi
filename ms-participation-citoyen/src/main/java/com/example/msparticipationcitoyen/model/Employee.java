package com.example.msparticipationcitoyen.model;

import com.example.msparticipationcitoyen.entities.Reply;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullNameLat;


    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private Collection<Reply> Reponces;

}
