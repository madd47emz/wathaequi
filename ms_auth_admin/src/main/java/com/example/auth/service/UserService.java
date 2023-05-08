package com.example.auth.service;



import com.example.auth.entities.Auth;
import com.example.auth.DTO.AuthDto;

import java.util.List;

public interface UserService {
    Auth save(AuthDto user);
    List<Auth> findAll();
    Auth findOne(String nin);
}