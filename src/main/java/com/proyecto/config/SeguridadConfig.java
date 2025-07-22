package com.proyecto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SeguridadConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
		
		http.authorizeHttpRequests(auth -> auth
				.anyRequest().permitAll() 
				).csrf(csrf -> csrf.disable())
		.formLogin(login -> login.disable());
		return http.build();
		
	}
	

}
