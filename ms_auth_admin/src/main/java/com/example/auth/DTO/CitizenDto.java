package com.example.auth.DTO;

import com.example.auth.entities.Citizen;
import com.example.auth.entities.Gender;
import com.example.auth.entities.Status;
import lombok.Data;

import java.util.Date;

@Data
public class CitizenDto{
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
    private  String gender;
    private String status;
}
