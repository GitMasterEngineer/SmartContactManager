package com.contact.smartcontactmanager.service;

import java.security.Signature;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	// use secrect key for JWT Token
	private static final String SECRET_KEY = "suyyvshbfhsbpfbsbflsvvlfbslhvoufvlbfsbvvbsbvlj";
	// we set Expiration time of JWT Token
	// if you want to do loose coupling then we can add this lines in
	// application.properties file
	private static final long ACCESS_TOKEN_EXPIRATION = 15 * 60 * 100;// 15 min valid
	private static final long REFRESH_TOKEN_EXPIRATION = 48 * 60 * 1000;// 28 hours valid

	// Generate token
	public String generateToken(String username, boolean isAccessToken) {
		// it will expiration of ACCESS token or REFRESh token
		long expiration = isAccessToken ? ACCESS_TOKEN_EXPIRATION : REFRESH_TOKEN_EXPIRATION;
		// Here our token will build
		return Jwts.builder().setSubject(username)// use name set
				.setIssuedAt(new Date())// date set
				.setExpiration(new Date(System.currentTimeMillis() + expiration))// time of token is set
				.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256).compact();
	}

	// get name from token
	public String getUserNameFromToken(String Token) {
		return Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(Token).getBody().getSubject();
	}

	// validate token
	// Exception might be occur so that why we use try catch block
	public boolean validateToken(String Token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(Token);
			return true;
		} catch (JwtException ex) {
			return false;
		}

	}

}
