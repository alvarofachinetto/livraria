package com.livros.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.livros.security.JWTAuthenticationFilter;
import com.livros.security.JwtAuthorizationFilter;
import com.livros.security.JwtUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService detailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private static final String [] PUBLIC_MATCHES_GET = {
			"/api/livros/**"
	};
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		
		security.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_MATCHES_GET).permitAll()
		.and().authorizeRequests().antMatchers(HttpMethod.POST, "/api/livros/**").permitAll()
		.and().authorizeRequests().antMatchers(HttpMethod.PUT, "/api/livros/**").permitAll()
		.and().authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/livros/**").permitAll().anyRequest().authenticated();
		security.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		security.addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtUtil, detailsService));
		security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public CorsConfigurationSource corsConfiguration() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	public void configPassword(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(detailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
