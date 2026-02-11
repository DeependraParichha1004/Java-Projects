package com.example.Quiz.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

// @Service
// public class JwtTokenService {

//     @Value("${spring.jwt.secretkey}")
//     private String secretkey;

//     @Value("${spring.jwt.expiration}")
//     private Long expiration;

//     public String generateJwtToken(String username, String password) {
//         Date now = new Date();
//         Date expiryDate = new Date(now.getTime() + expiration);
//         SecretKey key = Keys.hmacShaKeyFor(secretkey.getBytes(StandardCharsets.UTF_8));
//         Map<String, String> json_payload = new HashMap<>();
//         json_payload.put("name", username);
//         json_payload.put("password", password);
//         return Jwts.builder()
//                 .claims(json_payload)
//                 .subject("json_payload")
//                 .issuedAt(now)
//                 .expiration(expiryDate)
//                 .signWith(key) // Algorithm is inferred from the key type
//                 .compact();
//     }

//     public SecretKey getsecretkey() {
//         return Keys.hmacShaKeyFor(secretkey.getBytes(StandardCharsets.UTF_8));
//     }

//     public boolean validAuthToken(String auth_token) {

//         try {
//             Jwts.parser()
//                     .verifyWith(getsecretkey())
//                     .build()
//                     .parseSignedClaims(auth_token);

//             // If no exception was thrown, the token is structurally valid
//             // AND the expiration date is in the future.
//             return true;

//         } catch (ExpiredJwtException e) {
//             // Token is expired
//             return false;
//         }

//     }
// }

@Service
public class JwtTokenService {

    @Value("${spring.jwt.secretkey}")
    private String secretkey;

    @Value("${spring.jwt.expiration}")
    private Long expiration;

    private SecretKey getSigningKey() {
        // Ensure secretkey.getBytes() is at least 32 bytes!
        return Keys.hmacShaKeyFor(secretkey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJwtToken(String username, String password) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);
        Map<String, String> json_payload = new HashMap<>();
        json_payload.put("name", username);
        json_payload.put("password", password);
        return Jwts.builder()
                .claims(json_payload)
                // Password should NOT be in JWT (it's encoded, not encrypted)
                .subject(username)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    public boolean validAuthToken(String token) {
        try {
            // Remove "Bearer " if it exists
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            // Captures ExpiredJwtException, MalformedJwtException, and SignatureException
            return false;
        }
    }
}