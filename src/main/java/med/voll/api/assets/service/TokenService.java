package med.voll.api.assets.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import med.voll.api.assets.entity.User;
import med.voll.api.core.config.ValuesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Service
public class TokenService {
    @Autowired
    private ValuesConfig values;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(values.getUrlSecret());
            var token =  JWT.create()
                    .withIssuer("API DOCTOR")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expiresDate())
                    .sign(algorithm);
//                    .withClaim("id", user.getId())
//                    .withClaim("password", user.getPassword())
            log.info("Generating token {}", token);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("***Error generating token : " + e.getMessage(), e);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(values.getUrlSecret());
            return JWT.require(algorithm)
                    .withIssuer("API DOCTOR")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("***Invalid or expired JWT token!");
        }
    }

    private Instant expiresDate() {
        return LocalDateTime
                .now()
                .plusHours(2)
                .toInstant(
                        ZoneOffset.of("-03:00")
                );
    }
}
