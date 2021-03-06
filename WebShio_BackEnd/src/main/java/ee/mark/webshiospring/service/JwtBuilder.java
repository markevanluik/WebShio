package ee.mark.webshiospring.service;

import ee.mark.webshiospring.model.Person;
import ee.mark.webshiospring.model.output.AuthData;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
@Log4j2
public class JwtBuilder {

    @Value("${jwt.signingkey}")
    private String jwtSecretKey;

    public AuthData createJwtAuthToken(Person person) {
        LocalDateTime newTime = LocalDateTime.now().plusHours(2);
        Instant instant = newTime.toInstant(ZoneOffset.UTC);
        Date expiryDate = Date.from(instant);

        byte[] signingKey = jwtSecretKey.getBytes();
        String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setIssuer("webshio")
                .setSubject(person.getEmail())
                .setExpiration(expiryDate)
                .compact();
        AuthData authData = new AuthData();
        authData.setToken(token);
        authData.setExpirationDate(expiryDate);
        return authData;
    }
}
