package com.example.auth.dao;


import com.example.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
    Role findByName(String name);
}