package com.example.msgestiondocumentv2.Dto;

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
