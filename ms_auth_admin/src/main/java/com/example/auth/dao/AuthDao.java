package com.example.auth.dao;


import com.example.auth.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthDao extends JpaRepository<Auth, Long> {
    Auth findByUsername(String username);
    Boolean existsByUsername(String username);
}