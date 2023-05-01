package com.example.msgestiondocument.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.sql.Date;
import java.time.temporal.Temporal;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Document("papers")
public class Paper {
    @Id
    private String id;
    private String name;
    private String type;
    private Attachement file;

    List<Attachement> folder;

    private Date created;
    private Date expiration;

    private String userId;
    private String agentId;
    private String demandeId;
    private String OfficeId;
}
