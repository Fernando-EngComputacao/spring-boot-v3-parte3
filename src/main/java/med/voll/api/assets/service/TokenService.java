package med.voll.api.assets.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.extern.slf4j.Slf4j;
import med.voll.api.domain.user.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Slf4j
@Service
public class TokenService {

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456890");
            return JWT.create()
                    .withIssuer("API DOCTOR")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expiresDate())
                    .sign(algorithm);
//                    .withClaim("id", user.getId())
//                    .withClaim("password", user.getPassword())
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error generating token : " + e.getMessage(), e);
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
