package com.example.auth.service.impl;

import com.example.auth.dao.AuthDao;
import com.example.auth.model.Auth;
import com.example.auth.DTO.AuthDto;
import com.example.auth.model.Role;
import com.example.auth.service.RoleService;
import com.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthDao userDao;

    @Autowired
    BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Auth auth = userDao.findByUsername(username);
        if(auth == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(auth.getUsername(), auth.getPassword(), getAuthority(auth));
    }

    private Set<SimpleGrantedAuthority> getAuthority(Auth auth) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        auth.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public List<Auth> findAll() {
        List<Auth> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Auth findOne(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Auth save(AuthDto user) {

        Auth nAuth = user.getUserFromDto();
        nAuth.setPassword(bcryptEncoder.encode(user.getPassword()));

        Role role = roleService.findByName("USER");
        List<Role> roleSet = new ArrayList<>();
        roleSet.add(role);
        nAuth.setRoles(roleSet);
        return userDao.save(nAuth);
    }

}
