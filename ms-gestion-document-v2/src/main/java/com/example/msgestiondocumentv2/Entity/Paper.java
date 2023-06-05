package com.example.msgestiondocumentv2.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Document("papers")
public class Paper {
    @Id
    private String id;
    private String name;
    private String type;

    List<Attachement> folder;


    private java.util.Date created;
    private Date expiration;

    private String userId;
    private String agentId;
    private String demandeId;
    private String OfficeId;
}
