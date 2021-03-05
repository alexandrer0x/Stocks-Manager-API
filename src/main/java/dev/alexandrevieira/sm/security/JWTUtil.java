package dev.alexandrevieira.sm.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String email) {
		String token = Jwts.builder()
				.setSubject(email)
				.setExpiration(null)
				.signWith(SignatureAlgorithm.HS512 , this.secret.getBytes())
				.compact();
		
		return "Bearer " + token;
	}
}
