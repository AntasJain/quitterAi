package tech.antasjain.quitterAi.configs;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
@Component
public class JwtTokenUtil {
    @Value("${jwt.secret")
    private String secret;
    private final Key key = new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS512.getJcaName());

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }

    public boolean isTokenExpired(String token){
        return  extractClaims(token).getExpiration().before(new Date());
    }
    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }
}
