package com.hasun.toy_springboots.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
            .antMatchers("/").authenticated()
            .antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
            .anyRequest().permitAll();

        httpSecurity.formLogin().loginPage("/loginForm")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/");
        
        return httpSecurity.build(); 
    } 
}
