package com.example.auth.service;


import com.example.auth.model.Role;

public interface RoleService {
    Role findByName(String name);
}