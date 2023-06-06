<<<<<<< HEAD:ms-participation-citoyen/src/main/java/com/example/msparticipationcitoyen/config/SecurityConfig.java
package com.example.msparticipationcitoyen.config;
=======
package com.example.msdemandev3.config;
>>>>>>> 4611864c868eb1c003353b27d9aca9b9c20718c1:ms-demandeV3/ms-demandeV3/src/main/java/com/example/msdemandev3/config/SecurityConfig.java

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


}
