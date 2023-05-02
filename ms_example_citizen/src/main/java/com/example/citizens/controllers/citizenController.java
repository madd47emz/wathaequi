package com.example.citizens.controllers;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class citizenController {
    @PreAuthorize("hasRole('ROLE_CITIZEN')")
    @GetMapping("/header")
    public String getSomeResource() {
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
