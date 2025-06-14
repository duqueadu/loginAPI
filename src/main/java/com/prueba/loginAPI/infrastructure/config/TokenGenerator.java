package com.prueba.loginAPI.infrastructure.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenGenerator {
    private static final String SECRET_KEY = "tu_clave_secreta_segura_de_32_bytes";

    public static String generateToken(String email, Long id, Long role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }


    public static String extractToken(String authorizationHeader) {
        // Verifica si el header no es nulo y comienza con "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Elimina "Bearer " del header y retorna el token
            return authorizationHeader.substring(7); // Longitud de "Bearer " es 7
        }
        return null; // Retorna null si el token no es válido
    }

    public static Claims decodeToken(String token) {
        String secretKey = "tu_clave_secreta"; // Reemplaza con tu clave secreta

        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }



    public ResponseEntity<String> getTokenClaims(String token) {
        // Replace "yourSecretKey" with your actual secret key
        String secretKey = "yourSecretKey";

        // Parse the token to extract claims
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        // Constructing a response with claims
        return ResponseEntity.ok("Token Claims: " + claims.toString());
    }


    // Método para leer los claims del token
    public static Claims getClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }




}