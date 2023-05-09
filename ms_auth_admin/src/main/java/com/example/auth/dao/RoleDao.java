package com.example.auth.dao;


import com.example.auth.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
    Role findByName(String name);
}