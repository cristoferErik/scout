package com.checker.scout.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig{
    
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

     @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests((authz)->authz
        .requestMatchers("/login","/perform-login","/css/**", "/js/**").permitAll()
        .anyRequest()
        .authenticated())
        .sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
        .formLogin(form->form
        .loginPage("/login")
        .loginProcessingUrl("/perform-login")
        .permitAll())
        .logout(logout -> logout
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login?logout"))
            .build();
    }
}
