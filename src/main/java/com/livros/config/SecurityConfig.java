package com.livros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	private static final String [] PUBLIC_MATCHES_GET = {
			"/api/livros/**"
	};
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security.cors().and().csrf().disable();
		security.authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_MATCHES_GET).permitAll()
		.and().authorizeRequests().antMatchers(HttpMethod.POST, PUBLIC_MATCHES_GET).authenticated()
		.and().authorizeRequests().antMatchers(HttpMethod.PUT, PUBLIC_MATCHES_GET).authenticated()
		.and().authorizeRequests().antMatchers(HttpMethod.DELETE, PUBLIC_MATCHES_GET).authenticated();
	}
	
	@Bean
	public CorsConfigurationSource corsConfiguration() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}
