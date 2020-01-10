package com.livros.security;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private static String SECRET = "AssinarToken";
//	private static String PREFIX = "Bearer";
//	private static String HEADER_STRING = "Authorization";
	private static long EXPIRATION = 120000;
	
	//gera o token com as informações do usuario e tempo valido
	public static String generateToken(HttpServletResponse response, String username) {
		String token = Jwts.builder()
							.setSubject(username)
							.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
							.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
							.compact();
//		response.addHeader(HEADER_STRING, PREFIX + " "+ token);
		return token;
	}
	
	//verifica se o token do usuario e valido e não esta expirado
	public static boolean tokenValido(String token) {
//		String token = request.getHeader(HEADER_STRING);//obtem o token
		
		Claims claims = getClaims(token);
		
		if(token != null) {
			String username = claims.getSubject();
			Date expiration = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if(username != null && now.before(expiration)) {
//				return new UsernamePasswordAuthenticationToken(claims, null, Collections.emptyList());
				return true;
			}
		}
		return false;
	}

	//obtem as credenciais do token se não forem inválidas
	private static Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(SECRET.getBytes())
					.parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}
	//obtem o usuario a partir do token
	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			return claims.getSubject();
		}
		return null;
	}
	
}
