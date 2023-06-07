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
        return citizenDao.findCitizenByNin(nin);

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

        Agent agent =new Agent(null,agentDTO.getNin(),citizenDao.findCitizenByNin(agentDTO.getNin()).getFullNameLat(),agentDTO.getCommune(),agentDTO.getWilaya());
        Auth auth = userDao.findAuthByUsername(agentDTO.getNin());
        auth.getRoles().add(new Role(2L,"AGENT"));
        userDao.save(auth);
        agentDao.save(agent);

        return new ResponseEntity<>("Agent registered success!", HttpStatus.OK);

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
        citizen.setCommuneNaissance(registerDto.getCommuneNaissance());
        citizen.setDayraNaissance(registerDto.getDayraNaissance());
        citizen.setWilayaNaissance(registerDto.getWilayaNaissance());
        citizen.setNationality(registerDto.getNationality());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        List<Role> roles = new ArrayList<>();
        roles.add(roleDao.findByName("CITIZEN"));
        user.setRoles(roles);
        citizenDao.save(citizen);
        userDao.save(user);


        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/update/{nin}", method = RequestMethod.PATCH)
    public String update(@RequestBody CitizenDto payload, @PathVariable String nin){
        Citizen citizen=citizenDao.findCitizenByNin(nin);
        System.out.println(citizen);
        if (citizenDao.findCitizenByNin(nin)!=null){
            if(payload.getFullNameAr().equals("")){
                citizen.setFullNameAr(citizen.getFullNameAr());
            }
            else {
                citizen.setFullNameAr(payload.getFullNameAr());
            }
            if(payload.getFullNameLat().equals("")){
                citizen.setFullNameLat(citizen.getFullNameLat());
            }
            else {
                citizen.setFullNameLat(payload.getFullNameLat());
            }
            if(payload.getFather().equals("")){
                citizen.setFather(citizen.getFather());
            }
            else {
                citizen.setFather(payload.getFather());
            }
            if(payload.getMother().equals("")){
                citizen.setMother(citizen.getMother());
            }
            else {
                citizen.setMother(payload.getMother());
            }
            if(payload.getPartner().equals("")){
                citizen.setPartner(citizen.getPartner());
            }
            else {
                citizen.setPartner(payload.getPartner());
            }
            if(payload.getBirthdate().toString().equals("")){
                citizen.setBirthdate(citizen.getBirthdate());
            }
            else {
                citizen.setBirthdate(payload.getBirthdate());
            }
            if(payload.getCommune().equals("")){
                citizen.setCommune(citizen.getCommune());
            }
            else {
                citizen.setCommune(payload.getCommune());
            }
            if(payload.getDayra().equals("")){
                citizen.setDayra(citizen.getDayra());
            }
            else {
                citizen.setDayra(payload.getDayra());
            }
            if(payload.getWilaya().equals("")){
                citizen.setWilaya(citizen.getWilaya());
            }
            else {
                citizen.setWilaya(payload.getWilaya());
            }
            if(payload.getNationality().equals("")){
                citizen.setNationality(citizen.getNationality());
            }
            else {
                citizen.setNationality(payload.getNationality());
            }

        citizenDao.save(citizen);
        return "updated";}
        return "user not found";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/delete/{nin}", method = RequestMethod.DELETE)
    public String delete(@PathVariable String nin){
        if(citizenDao.findCitizenByNin(nin)!=null){
        Auth auth=userDao.findByUsername(nin);
        Agent agent =agentDao.findAgentByNin(nin);
        if(agent != null){
            agentDao.delete(agent);
        }
        Citizen citizen =citizenDao.findCitizenByNin(nin);
        citizenDao.delete(citizen);
        userDao.delete(auth);
        return "deleted";}
        return "user not found";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/deleteAgent/{nin}", method = RequestMethod.DELETE)
    public String deleteAgent(@PathVariable String nin) {


        Agent agent = agentDao.findAgentByNin(nin);
        Auth auth =userDao.findAuthByUsername(nin);


        if (agent != null) {


            agentDao.delete(agent);
            auth.getRoles().remove(new Role(2L, "AGENT"));
            System.out.println(auth.getRoles());
            return "deleted";
        }else {
            return "agent not found";
        }


    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/updateAgent/{nin}", method = RequestMethod.PATCH)
    public String updateAgent(@RequestBody AgentDto payload, @PathVariable String nin){

        Agent agent= agentDao.findAgentByNin(nin);
        if (agent !=null){
            agent.setCommune(payload.getCommune());
            if (payload.getWilaya().equals(agent.getWilaya())){
                agent.setWilaya(agent.getWilaya());
            }
            agent.setWilaya(payload.getWilaya());
            agent.setNin(agent.getNin());
            agent.setFullName(agent.getFullName());
            agent.setId(agent.getId());
            agentDao.save(agent);
            return "updated";
        }

        return "user not found";
    }


}
