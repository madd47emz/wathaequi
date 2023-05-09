package com.example.auth.service;


import com.example.auth.entities.Role;

public interface RoleService {
    Role findByName(String name);
}