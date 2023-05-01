package com.example.msgestiondocument.Dto;

import com.example.msgestiondocument.Entity.Attachement;
import lombok.Data;

import java.sql.Date;

@Data
public class PaperRequest {
    private String name;
    private String type;
    private Date created;
    private Date expiration;
    private String userId;
    private String agentId;
    private String demandeId;
}
