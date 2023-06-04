package com.example.auth.controller;


import com.example.auth.DTO.AgentDto;
import com.example.auth.DTO.CitizenDto;
import com.example.auth.DTO.registerDTO;
import com.example.auth.config.TokenProvider;
import com.example.auth.dao.AgentDao;
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
import java.util.Objects;


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
    @Autowired
    AgentDao agentDao;

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
    @GetMapping("/profile/{nin}")
    public Citizen getProfile(@PathVariable("nin") String nin){
        return citizenDao.findCitizensByNin(nin);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/registerAgent" , method = RequestMethod.POST)
    public  ResponseEntity<String> registerAgent(@RequestBody AgentDto agentDTO ){
        if (!citizenDao.existsByNin(agentDTO.getNin())) {
            return new ResponseEntity<>("nin is not found !", HttpStatus.BAD_REQUEST);
        }
        if (agentDao.existsByNin(agentDTO.getNin())) {
            return new ResponseEntity<>("nin is taken!", HttpStatus.BAD_REQUEST);
        }
        userDao.findAuthByUsername(agentDTO.getNin()).getRoles().forEach(role -> {
            if(role.getName().equals("AGENT")){
                Agent agent =new Agent(null,agentDTO.getNin(),citizenDao.findCitizenByNin(agentDTO.getNin()).getFullNameLat(),agentDTO.getCommune());
                agentDao.save(agent);
            }
        });
        return new ResponseEntity<>("User registered success!", HttpStatus.OK);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody registerDTO registerDto) {
        if (userDao.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("nin is taken!", HttpStatus.BAD_REQUEST);
        }

        Auth user = new Auth();
        Citizen citizen = new Citizen();
        citizen.setNin(registerDto.getUsername());
        citizen.setFullNameLat(registerDto.getFullNameLat());
        citizen.setFullNameAr(registerDto.getFullNameAr());
        citizen.setFather(registerDto.getFather());
        citizen.setMother(registerDto.getMother());
        citizen.setFather(registerDto.getPartner());
        System.out.println(registerDto.getGender());
        citizen.setGender(Gender.valueOf(registerDto.getGender()));
        citizen.setStatus(Status.valueOf(registerDto.getStatus()));
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
        user.setRoles(roles);
        citizenDao.save(citizen);
        userDao.save(user);


        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/update/{nin}", method = RequestMethod.PATCH)
    public String update(@RequestBody CitizenDto payload, @PathVariable String nin){
        Citizen citizen=citizenDao.findCitizensByNin(nin);
        if (citizenDao.findCitizensByNin(nin)!=null){
        citizen.setFullNameAr(payload.getFullNameAr());
        citizen.setFullNameLat(payload.getFullNameLat());
        citizen.setFather(payload.getFather());
        citizen.setMother(payload.getMother());
        citizen.setPartner(payload.getPartner());
        citizen.setFather(payload.getFather());
            citizen.setGender(Gender.valueOf(payload.getGender()));

        citizen.setStatus(Status.valueOf(payload.getStatus()));
        citizen.setBirthdate(payload.getBirthdate());
        citizen.setCommune(payload.getCommune());
        citizen.setDayra(payload.getDayra());
        citizen.setWilaya(payload.getWilaya());
        citizen.setNationality(payload.getNationality());
        citizenDao.save(citizen);
        return "updated";}
        return "user not found";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/delete/{nin}", method = RequestMethod.DELETE)
    public String delete(@PathVariable String nin){
        if(citizenDao.findCitizensByNin(nin)!=null){
        Auth auth=userDao.findByUsername(nin);
        Citizen citizen =citizenDao.findCitizensByNin(nin);
        citizenDao.delete(citizen);
        userDao.delete(auth);
        return "deleted";}
        return "user not found";
    }
}
