package com.livros.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.livros.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService detailsService;
	
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
		security.addFilter(new JwtAuthenticationFilter());
	}
	
	@Bean
	public CorsConfigurationSource corsConfiguration() {
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
