package com.example.msgestiondemandedocuments_v1.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact implements Serializable{


        private String Telephone;
        private String Email;


}
