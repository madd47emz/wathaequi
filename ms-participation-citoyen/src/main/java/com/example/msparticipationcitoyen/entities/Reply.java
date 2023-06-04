package com.example.msparticipationcitoyen.entities;

import com.example.msparticipationcitoyen.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReply;

    private Date dateReply;

    private String Picture;

    @ManyToOne
    private Publication publication;
    private String Content;

    private Long idAgent;


    //********************************************************* utilisateur
    private String employeeName;
}
