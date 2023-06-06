package com.example.auth.dao;


import com.example.auth.entities.Auth;
import com.example.auth.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthDao extends JpaRepository<Auth, Long> {
    Auth findByUsername(String username);
    Boolean existsByUsername(String username);
    Auth findAuthByUsername(String username);
}