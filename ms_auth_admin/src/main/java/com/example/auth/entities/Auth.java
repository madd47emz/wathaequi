package com.example.auth.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES",
            joinColumns = {
            @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID") })
    private List<Role> roles;
}