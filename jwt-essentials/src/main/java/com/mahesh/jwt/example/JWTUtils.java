package com.mahesh.jwt.example;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtils {
    
    public static final String SECRET_KEY = "maheshverysecretmaheshverysecretmaheshverysecret";
    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Method to generate token
    public String generateToken(String userId) {
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + 1000 * 60 * 60;
        Date exp = new Date(expMillis);

        return Jwts.builder()
                   .setSubject(userId)
                   .setIssuedAt(new Date(nowMillis))
                   .setExpiration(exp)
                   .signWith(key, SignatureAlgorithm.HS256) // Sign with Key object
                   .compact();
    }

   
   
	public static Claims extractClaims(String token) {
        
		Jws<Claims> jwsClaims = Jwts.parserBuilder()   // Use parserBuilder for version 0.11.5+
                                     .setSigningKey(key)
                                     .build()           // Build the JwtParser object
                                     .parseClaimsJws(token);
        return jwsClaims.getBody();
    }
   
    public boolean validateToken(String token) {
        String[] parts = token.split("\\.");
//        if (parts.length != 3) {
//            System.out.println("Unexpected token length");
//            return false;
//        }
        return true;
    }

    
    public String extractUserId(String token) {
        Claims claims = extractClaims(token);
        return claims.getSubject();
    }
}
