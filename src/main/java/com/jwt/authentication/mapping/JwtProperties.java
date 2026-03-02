package com.jwt.authentication.mapping;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class JwtProperties {
	
	@Value("${jwt.key}")
	private String key;
	
	@Value("${jwt.expiration}")
	private long  expiration;

}
