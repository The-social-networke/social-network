package com.socialnetwork.project.security.jwt;

import com.socialnetwork.project.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtProvider {

    @Value("$(security.jwt.secret)")
    private String jwtSecret;

    @Value("$(security.jwt.expiration)")
    private String expiration;

    public String generateToken(CustomUserDetails user) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("email", user.getUsername());
        claims.put("roles", user.getAuthorities());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("gfgf");
        }
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.get("email", String.class);
    }
}
