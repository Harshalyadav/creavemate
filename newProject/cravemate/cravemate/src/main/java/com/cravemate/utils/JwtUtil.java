//package com.cravemate.utils;
//
//import java.util.Date;
//
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Component
//public class JwtUtil {
//	  private final String SECRET_KEY = "mysecretkey";
//
//	    public String generateToken(String email) {
//	        return Jwts.builder()
//	                .setSubject(email)
//	                .setIssuedAt(new Date())
//	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
//	                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//	                .compact();
//	    }
//}
