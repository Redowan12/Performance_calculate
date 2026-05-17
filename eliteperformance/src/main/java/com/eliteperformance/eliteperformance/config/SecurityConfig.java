package com.eliteperformance.eliteperformance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> {});

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        var employee = User.builder()
                .username("alice")
                .password(passwordEncoder().encode("pass123"))
                .roles("EMPLOYEE")
                .build();

        var manager = User.builder()
                .username("bob")
                .password(passwordEncoder().encode("pass123"))
                .roles("MANAGER")
                .build();

        var admin = User.builder()
                .username("carol")
                .password(passwordEncoder().encode("pass123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(employee, manager, admin);
    }}