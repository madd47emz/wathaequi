package com.example.auth;

import com.example.auth.dao.AuthDao;
import com.example.auth.dao.CitizenDao;
import com.example.auth.dao.RoleDao;
import com.example.auth.entities.Auth;
import com.example.auth.entities.Citizen;
import com.example.auth.entities.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.server.Session;

import com.example.auth.dao.AuthDao;
import com.example.auth.dao.CitizenDao;
import com.example.auth.dao.RoleDao;
import com.example.auth.entities.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class AuthApplication implements CommandLineRunner {
    @Autowired
    private AuthDao authDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CitizenDao citizenDao;
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {


    }
}
