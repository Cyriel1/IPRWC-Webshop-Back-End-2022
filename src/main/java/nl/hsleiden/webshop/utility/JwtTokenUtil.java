package nl.hsleiden.webshop.utility;

import java.util.*;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.*;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import nl.hsleiden.webshop.entity.UserDetailsImpl;

@Component
public class JwtTokenUtil {

    @Value("${jwt.expiration.ms}")
    private int jwtExperationMilliseconds;

    @Value("${jwt.secret}")
    private String secret;

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetailsImpl userDetails, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userDetails.getId());
        claims.put("email", userDetails.getEmail());
        claims.put("roles", roles);
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExperationMilliseconds))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException signatureException) {
            logger.error("Invalid JWT signature: ", signatureException.getMessage());
        } catch (MalformedJwtException malformedJwtException) {
            logger.error("Invalid JWT token: ", malformedJwtException.getMessage());
        } catch (ExpiredJwtException expiredJwtException) {
            logger.error("JWT token is expired: ", expiredJwtException.getMessage());
        } catch (UnsupportedJwtException unsupportedJwtException) {
            logger.error("JWT token is unsupported: ", unsupportedJwtException.getMessage());
        } catch (IllegalArgumentException illegalArgumentException) {
            logger.error("JWT claims string is empty: ", illegalArgumentException.getMessage());
        }

        return false;
    }
}
