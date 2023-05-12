package com.example.auth.DTO;
import com.example.auth.entities.Gender;
import com.example.auth.entities.Status;
import lombok.Data;

import java.util.Date;

@Data
public class registerDTO {
    private String username;
    private String password;
    private Boolean admin;
    private Boolean user;
    private Boolean agent;
    private Date birthdate;
    private String status;
    private String gender;
    private String nationality;
    private String fullName;
    private String famillyName;
    private  String firstName;
    private String dayra;
    private String commune;
    private String wilaya;

}
