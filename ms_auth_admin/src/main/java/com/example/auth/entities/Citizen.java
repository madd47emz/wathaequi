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
    private String name;
    private String nin;
    private Date birthdate;
    private  String gender;
    private  String status;
}
