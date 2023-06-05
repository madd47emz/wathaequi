package com.example.auth.entities;

import com.example.auth.util.AlgerianNINGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
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
    private String dayraNaissance;
    private String communeNaissance;
    private String wilayaNaissance;
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    private  Gender gender;
    private Status status;

}
