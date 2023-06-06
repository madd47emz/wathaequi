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
        admin.setPassword(passwordEncoder.encode("hidaya"));
        admin.setUsername("admin");
        admin.setRoles(roles);
        authDao.save(admin);

        Citizen citizen = new Citizen();
        citizen.setId(1L);
        citizen.setNin("admin");
        citizen.setNationality("Algerian");
        citizen.setFullNameLat("Bouabdelli lamis");
        citizen.setFullNameAr("بوعبدلي لميس");
        citizen.setFather("Father Name");
        citizen.setMother("Mother Name");
        citizen.setPartner("Partner Name");
        citizen.setDayra("Dayra Name");
        citizen.setCommune("Commune Name");
        citizen.setWilaya("Wilaya Name");
        citizen.setBirthdate(new Date());
        citizen.setGender(Gender.FEMALE);
        citizen.setStatus(Status.valueOf("Single"));

        citizenDao.save(citizen);
       /*Agent agent = new Agent();
        agent.setId(1L);
        agent.setNin("admin");
        agent.setFullName("Bouabdelli lamis");
        agent.setCommune("Sidi Bel Abess");
        agentDao.save(agent);*/
<<<<<<< HEAD
=======


>>>>>>> 4611864c868eb1c003353b27d9aca9b9c20718c1


    }
}
