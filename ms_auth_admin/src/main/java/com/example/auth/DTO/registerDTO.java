package com.example.auth.DTO;
import lombok.Data;

import java.util.Date;

@Data
public class registerDTO {
    private String username;
    private String name;
    private String password;
    private Boolean admin;
    private Boolean user;
    private Boolean agent;
    private Date birthdate;
    private String status;
    private String gender;




}
