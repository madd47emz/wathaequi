package com.example.msgestiondocument.Entity;

import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import java.io.InputStream;


@Data @NoArgsConstructor @AllArgsConstructor
@Document
public class Attachement{

    @Id
    private String id;

    private String filename;

    private long length;

    private String contentType;

}
