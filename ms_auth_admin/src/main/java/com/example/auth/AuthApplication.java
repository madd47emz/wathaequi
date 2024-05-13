package com.example.auth;

import com.example.auth.dao.*;
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
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AuthApplication implements CommandLineRunner {
    @Autowired
    private AuthDao authDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CitizenDao citizenDao;
    @Autowired
    AgentDao agentDao;
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        roleDao.save(new Role(1L,"ADMIN"));
        roleDao.save(new Role(2L,"AGENT"));
        roleDao.save(new Role(3L,"CITIZEN"));
        List<Role> roles = new ArrayList<>();
        roles.add(roleDao.findByName("ADMIN"));
        roles.add(roleDao.findByName("AGENT"));
        roles.add(roleDao.findByName("CITIZEN"));

        Auth admin= new Auth();
        admin.setId(1L);
        admin.setPassword(passwordEncoder.encode("0798800970"));
        admin.setUsername("mohamed");
        admin.setRoles(roles);
        authDao.save(admin);


    }
}
