package soc.ms.msauth.impl.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import soc.ms.msauth.interfaces.utils.IJWTUtils;
import soc.ms.msauth.interfaces.utils.JWTUser;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JWTUtils implements IJWTUtils {
    // Set to store invalidated tokens
    private Set<String> _invalidatedTokens = ConcurrentHashMap.newKeySet();

    @Value("${jwt.secret}")
    private String _jwtSecret;

    @Value("${jwt.expiration}")
    private int _jwtExpirationMs;

    @Override
    public String createToken(JWTUser user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", user.userName());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.userName())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + _jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            // Check if token is invalidated
            if (_invalidatedTokens.contains(token)) {
                return false;
            }

            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            // Invalid JWT token
            return false;
        } catch (ExpiredJwtException e) {
            // JWT token is expired
            return false;
        } catch (UnsupportedJwtException e) {
            // JWT token is unsupported
            return false;
        } catch (IllegalArgumentException e) {
            // JWT claims string is empty
            return false;
        } catch (Exception e) {
            // Invalid JWT signature
            return false;
        }
    }

    @Override
    public void invalidateToken(String token) {
        _invalidatedTokens.add(token);
        // You might want to periodically clean up expired tokens from invalidatedTokens
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = _jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Optional: Method to clean up expired tokens from invalidatedTokens set
    public void cleanupExpiredTokens() {
        _invalidatedTokens.removeIf(token -> {
            try {
                Jwts.parserBuilder()
                        .setSigningKey(getSigningKey())
                        .build()
                        .parseClaimsJws(token);
                return false;
            } catch (ExpiredJwtException e) {
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }
}
