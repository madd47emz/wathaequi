package com.example.auth.DTO;

import com.example.auth.entities.Citizen;
import lombok.Data;

import java.util.Date;

@Data
public class CitizenDto extends Citizen {
    private String name;
    private Date birthdate;
    private  String gender;
    private  String status;

}
