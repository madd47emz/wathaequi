package com.example.msparticipationcitoyen.model;

import com.example.msparticipationcitoyen.entities.Publication;
import com.example.msparticipationcitoyen.entities.TypePublication;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.Date;


@Data
public class ReplyRequest {


    private Date dateReply;

    @Lob
    private byte[] picture;

    private Long idPublication;
    private String Content;
}
