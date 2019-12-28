package com.livros.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.livros.service.H2Service;

@Profile("prod")
@Configuration
public class DbConfig {
	
	@Autowired
	private H2Service h2Service;
	
	@Bean
	public boolean instantiateDatabase() throws Exception {
		h2Service.loadH2();
		return true;
	}
	
	
}
