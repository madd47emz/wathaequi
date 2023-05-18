package com.example.msnotification.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class UserContact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nin;

    private String name;

    @Embedded
    private Contact contact;
}
