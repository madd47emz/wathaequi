package com.example.msgestiondocumentv2.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data @NoArgsConstructor @AllArgsConstructor
@Document
public class Attachement{

    @Id
    private String id;

    private String filename;

    private long length;

    private String contentType;

}
