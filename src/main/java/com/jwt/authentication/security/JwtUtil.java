package com.jwt.authentication.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.jwt.authentication.config.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	
	private final JwtProperties jwtProperties;
public JwtUtil(JwtProperties jwtProperties)	
{
	this.jwtProperties=jwtProperties;
}
	
public String generateToken(String username,String role)
{
	return Jwts.builder()
			.setSubject(username)
			.claim("role",role)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis()+ jwtProperties.getExpiration()))
			.signWith(getSignKey(),SignatureAlgorithm.HS256)
			.compact();
}

private SecretKey getSignKey() {
	byte[] keyBytes=Decoders.BASE64.decode(jwtProperties.getKey());
	return Keys.hmacShaKeyFor(keyBytes);
}

private Claims extractClaims(String token)
{
	return Jwts.parserBuilder()
			.setSigningKey(getSignKey())
			.build()
			.parseClaimsJws(token)
			.getBody();
}

public String extractUsername(String token)
{
	return extractClaims(token).getSubject();
}

public boolean validateToken(String token, String username)
{
	String tokenUsername=extractUsername(token);
	return tokenUsername.equals(username) && !isTokenExpired(token);
}

private boolean isTokenExpired(String token) {
	return extractClaims(token)
			.getExpiration()
			.before(new Date());
			
}

}
