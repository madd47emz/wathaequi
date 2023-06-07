package com.example.msparticipationcitoyen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Lob
    private byte[] picture;

    @JsonIgnore
    @ManyToOne
    private Publication publication;
    private String Content;
}
