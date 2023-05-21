package com.example.msgestiondocumentv2.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class Citizen {
    private String nin;
    private String nationality;
    private String fullNameLat;
    private String fullNameAr;
    private String father;
    private String mother;
    private String partner;
    private String dayra;
    private String commune;
    private String wilaya;
    private Date birthdate;
    private String gender;
    private String status;
}
