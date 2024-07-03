package com.example.demo.config;

import com.example.demo.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(); // Implement this class as needed
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Disable CSRF for stateless APIs
                .authorizeRequests(authz -> authz
                        .antMatchers("/api/users/**").permitAll()  // Allow all requests to user-related endpoints
                        .antMatchers("/password/**").permitAll()
                        .antMatchers("/api/gini/**").permitAll()
                        .antMatchers("/api/reports/**").permitAll()
                        .antMatchers("/api/reports").permitAll()
                        .anyRequest().authenticated())  // Secure all other endpoints
                .httpBasic();  // Use basic authentication
        return http.build();
    }
}
