package com.livros.security;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private static String SECRET = "AssinarToken";
	private static String PREFIX = "Bearer";
	private static String HEADER_STRING = "Authorization";
	private static long EXPIRATION = 86400000;
	
	public static String generateToken(HttpServletResponse response, String username) {
		String token = Jwts.builder()
							.setSubject(username)
							.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
							.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
							.compact();
		
		response.addHeader(HEADER_STRING, PREFIX + " "+ token);
		return token;
	}
	
	public static Authentication tokenValido(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);//obtem o token
		
		Claims claims = getClaims(token);//obtem as reinvidicacoes
		
		if(token != null) {
			String username = claims.getSubject();
			Date expiration = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if(username != null && now.before(expiration)) {
				return new UsernamePasswordAuthenticationToken(claims, null, Collections.emptyList());
			}
		}
		return null;
	}

	private static Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(SECRET.getBytes())
					.parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}
	
}
