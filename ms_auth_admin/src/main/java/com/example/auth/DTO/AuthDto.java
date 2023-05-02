package com.example.auth.DTO;

import com.example.auth.model.Auth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {
    
    private String username;
    private String password;



    public Auth getUserFromDto(){
        Auth auth = new Auth();
        auth.setUsername(username);
        auth.setPassword(password);
        return auth;
    }
    
}