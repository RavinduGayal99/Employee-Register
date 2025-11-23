package practical.employee.register.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil
{
    private final long ONE_DAY_EXPIRATION_TIME = 86400000;

    public String generateToken(String username)
    {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ONE_DAY_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKeyGenerator())
                .compact();
    }

    public boolean validateToken(String token, String username)
    {
        final String extractUsername = extractUsername(token);
        return (username.equals(extractUsername) && !isTokenExpired(token));
    }

    public String extractUsername(String token)
    {
        return Jwts.parser().setSigningKey(secretKeyGenerator()).parseClaimsJws(token).getBody().getSubject();
    }

    private boolean isTokenExpired(String token)
    {
        return Jwts.parser().setSigningKey(secretKeyGenerator()).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    private byte[] secretKeyGenerator()
    {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32];
        random.nextBytes(key);
        String secretKey = Base64.getEncoder().encodeToString(key);
        return secretKey.getBytes();
    }
}
