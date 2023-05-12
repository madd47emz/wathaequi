package com.example.auth.DTO;

import com.example.auth.entities.Citizen;
import com.example.auth.entities.Gender;
import com.example.auth.entities.Status;
import lombok.Data;

import java.util.Date;

@Data
public class CitizenDto extends Citizen {
    private String nationality;
    private String fullName;
    private String famillyName;
    private  String firstName;
    private String dayra;
    private String commune;
    private String wilaya;
    private Date birthdate;
    private String gender1;
    private String status1;
}
