package io.github.mennakhalilselim.taskmanagementsystem.security.service;

import io.github.mennakhalilselim.taskmanagementsystem.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtProperties properties;

    public Optional<String> extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> Optional<T> extractClaim(String token, Function<Claims, T> claimsResolver) {
        return extractAllClaims(token)
                .map(claimsResolver);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(Map.of(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + properties.getExpirationMs()))
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return extractUsername(token)
                .filter(username -> username.equals(userDetails.getUsername()))
                .filter(username -> !isTokenExpired(token))
                .isPresent();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token)
                .map(expiration -> expiration.before(new Date()))
                .orElse(true);
    }

    private Optional<Date> extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Optional<Claims> extractAllClaims(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return Optional.of(claims);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(properties.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
