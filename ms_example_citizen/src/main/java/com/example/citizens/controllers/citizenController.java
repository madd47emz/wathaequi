package com.example.citizens.controllers;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class citizenController {
    @PreAuthorize("hasRole('ROLE_CITIZEN')")
    @GetMapping("/header")
    public String getSomeResource() {
        return "hiii i am Citizen";
    }
    @GetMapping("/headerr")
    public String getrce() {
        return "hiii i am Citizen";
    }
    @PreAuthorize("hasRole('ROLE_AGENT')")
    @GetMapping("/agent" )
    public String getResource() {
        return "hiii i am Agent";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/adminping", method = RequestMethod.GET)
    public String adminPing(){
        return "Only Admins Can Read This";
    }
    @PreAuthorize("hasAnyRole('ROLE_AGENT','ROLE_ADMIN')")
    @GetMapping("/ping" )
    public String getMe() {
        return "hiii i am Agent and admin";
    }
}
