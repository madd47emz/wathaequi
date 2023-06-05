package com.example.auth.DTO;
import com.example.auth.entities.Gender;
import com.example.auth.entities.Status;
import lombok.Data;

import java.util.Date;

@Data
public class registerDTO {
    private String username;
    private String password;
    private Date birthdate;
    private String status;
    private String gender;
    private String nationality;
    private String fullNameLat;
    private String fullNameAr;
    private String father;
    private String dayraNaissance;
    private String communeNaissance;
    private String wilayaNaissance;
    private String mother;
    private String partner;
    private String dayra;
    private String commune;
    private String wilaya;

}
