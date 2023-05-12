package com.example.auth.controller;


import com.example.auth.DTO.CitizenDto;
import com.example.auth.DTO.registerDTO;
import com.example.auth.config.TokenProvider;
import com.example.auth.dao.AuthDao;
import com.example.auth.dao.CitizenDao;
import com.example.auth.dao.RoleDao;
import com.example.auth.entities.*;
import com.example.auth.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;
    @Autowired
    private AuthDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private CitizenDao citizenDao;
    @Autowired
    TokenProvider tokenProvider;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        if(authentication.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(authentication);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,token).body(token);
        }
        else{
            throw new RuntimeException("invalid access");
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody registerDTO registerDto) {
        if (userDao.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        Auth user = new Auth();
        Citizen citizen = new Citizen();
        citizen.setNin(registerDto.getUsername());
        citizen.setFirstName(registerDto.getFirstName());
        citizen.setFamillyName(registerDto.getFullName());
        citizen.setFullName(registerDto.getFullName());
        if (registerDto.getGender() == "MALE"){
            citizen.setGender(Gender.MALE);
        } else if (registerDto.getGender() == "FEMALE") {
            citizen.setGender(Gender.FEMALE);
        }
       switch (registerDto.getStatus()){
           case "single":
               citizen.setStatus(Status.Single);
               break;
           case  "married":
               citizen.setStatus(Status.Married);
               break;
           case  "dead":
               citizen.setStatus(Status.Dead);
           case  "widow":
               citizen.setStatus(Status.Widow);
               break;
           case "divorcee":
               citizen.setStatus(Status.Divorcee);
               break;
       }
        citizen.setBirthdate(registerDto.getBirthdate());
        citizen.setCommune(registerDto.getCommune());
        citizen.setDayra(registerDto.getDayra());
        citizen.setWilaya(registerDto.getWilaya());
        citizen.setNationality(registerDto.getNationality());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        List<Role> roles = new ArrayList<>();
        if (registerDto.getAdmin() == true) {
            roles.add(roleDao.findByName("ADMIN"));
        }
        if (registerDto.getUser() == true) {
            roles.add(roleDao.findByName("CITIZEN"));
        }
        if (registerDto.getAgent() == true) {
            roles.add(roleDao.findByName("AGENT"));
        }
        user .setRoles(roles);
        citizenDao.save(citizen);
        userDao.save(user);


        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/update/{nin}", method = RequestMethod.PATCH)
    public String update(@RequestBody CitizenDto citizenDto, @PathVariable String nin){
        Citizen citizen=citizenDao.findCitizensByNin(nin);
        citizen.setFirstName(citizenDto.getFirstName());
        citizen.setFullName(citizenDto.getFullName());
        citizen.setFamillyName(citizenDto.getFamillyName());
        if (citizenDto.getGender1() == "MALE"){
            citizen.setGender(Gender.MALE);
        } else if (citizenDto.getGender1() == "FEMALE") {
            citizen.setGender(Gender.FEMALE);
        }

        if(citizenDto.getStatus1()== "single"){
            citizen.setStatus(Status.Single);
        }else if (citizenDto.getStatus1()== "Married"){
            citizen.setStatus(Status.Married);
        }else if (citizenDto.getStatus1()== "dead"){
            citizen.setStatus(Status.Dead);
        }else if (citizenDto.getStatus1()== "widow"){
            citizen.setStatus(Status.Widow);
        }else if(citizenDto.getStatus1()=="divorcee"){
            citizen.setStatus(Status.Divorcee);
        }
        citizen.setBirthdate(citizenDto.getBirthdate());
        citizen.setCommune(citizenDto.getCommune());
        citizen.setDayra(citizenDto.getDayra());
        citizen.setWilaya(citizenDto.getWilaya());
        citizen.setNationality(citizenDto.getNationality());
        citizenDao.save(citizen);
        return "updated";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/delete/{nin}", method = RequestMethod.DELETE)
    public String delete(@PathVariable String nin){
        Auth auth=userDao.findByUsername(nin);
        Citizen citizen =citizenDao.findCitizensByNin(nin);
        citizenDao.delete(citizen);
        userDao.delete(auth);
        return "deleted";
    }
}
