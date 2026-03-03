package com.jwt.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.authentication.filter.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    
	@Bean
	public SecurityFilterChain securityFilterChain(
	        HttpSecurity http,
	        JwtAuthenticationFilter jwtAuthenticationFilter
	) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(request -> request
	                .requestMatchers("/auth/**").permitAll()
	                .anyRequest().authenticated()
	        )
	        .sessionManagement(session ->
	                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )
	        .addFilterBefore(jwtAuthenticationFilter,
	                UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder()
	{
		return  new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
	        AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	}
}
