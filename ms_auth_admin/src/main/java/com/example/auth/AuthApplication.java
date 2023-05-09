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

        roleDao.save(new Role(1L,"ADMIN"));
        roleDao.save(new Role(2L,"AGENT"));
        roleDao.save(new Role(3L,"CITIZEN"));
        List<Role> roles = new ArrayList<>();
        roles.add(roleDao.findByName("ADMIN"));
        roles.add(roleDao.findByName("AGENT"));
        roles.add(roleDao.findByName("CITIZEN"));

        Auth admin= new Auth();
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("hidaya"));
        admin.setRoles(roles);
        authDao.save(admin);
        Citizen citizen = new Citizen();
        citizen.setNin("873498732");
        citizen.setGender("men");
        citizen.setName("fer");
        citizen.setStatus("single");
        citizenDao.save(citizen);
        List<Citizen> citizens=  citizenDao.findCitizensByGender("women");
        System.out.println(citizens);
    }
}
