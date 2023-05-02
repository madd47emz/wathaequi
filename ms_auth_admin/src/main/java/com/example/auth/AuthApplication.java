package com.example.auth;

import com.example.auth.dao.AuthDao;
import com.example.auth.dao.CitizenDao;
import com.example.auth.dao.RoleDao;
import com.example.auth.model.Auth;
import com.example.auth.model.Citizen;
import com.example.auth.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
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
        admin.setPassword(passwordEncoder.encode("hidaya"));
        admin.setUsername("admin");
        admin.setRoles(roles);
        authDao.save(admin);
        Citizen citizen = new Citizen();
        citizen.setId(1L);
        citizen.setGender("men");
        citizen.setName("fer");
        citizen.setStatus("single");
        citizen.setNin("2884793874");
        citizenDao.save(citizen);
       List<Citizen> citizens=  citizenDao.findCitizensByGender("women");
        System.out.println(citizens);



    }
}
