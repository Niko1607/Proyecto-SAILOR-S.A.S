package com.sailor.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret:my_super_secret_key_for_sailor_2024_must_be_long_enough}")
    private String jwtSecret;

    @Value("${jwt.expiration:86400000}")
    private long jwtExpirationMs;

    // Generar token
    public String generarToken(Long usuarioId, String correo, String rol) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.builder()
                .setSubject(correo)
                .claim("usuarioId", usuarioId)
                .claim("rol", rol)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Validar token
    public boolean validarToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Obtener correo
    public String obtenerCorreo(String token) {
        Claims claims = obtenerClaims(token);
        return claims.getSubject();
    }

    // Obtener usuarioId
    public Long obtenerUsuarioId(String token) {
        Claims claims = obtenerClaims(token);
        return claims.get("usuarioId", Long.class);
    }

    // Obtener rol
    public String obtenerRol(String token) {
        Claims claims = obtenerClaims(token);
        return claims.get("rol", String.class);
    }

    // Método reutilizable (PRO)
    private Claims obtenerClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}