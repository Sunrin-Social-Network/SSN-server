package io.twotle.ssn.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.twotle.ssn.component.CustomException;
import io.twotle.ssn.component.ExceptionCode;
import io.twotle.ssn.component.RedisDao;
import io.twotle.ssn.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final ObjectMapper objectMapper;
    private final RedisDao redisDao;

    @Value("${spring.jwt.key}")
    private String key;

    @Value("${spring.jwt.live.atk}")
    private Long atkLive;

    @Value("${spring.jwt.live.rtk}")
    private Long rtkLive;

    @PostConstruct
    protected void init() {
        key = Base64.getEncoder().encodeToString(key.getBytes());
    }

    public TokenResponseDTO createTokensByLogin(User user) throws JsonProcessingException {
        Subject atkSubject = Subject.access(
                user.getId(),
                user.getEmail(),
                user.getUsername());
        Subject rtkSubject = Subject.refresh(
                user.getId(),
                user.getEmail(),
                user.getUsername());
        String atk = createToken(atkSubject, atkLive);
        String rtk = createToken(rtkSubject, rtkLive);
        redisDao.setValues(user.getEmail(), rtk, Duration.ofMillis(rtkLive));
        return new TokenResponseDTO(atk, rtk);
    }

    public TokenResponseDTO refresh(User user) throws JsonProcessingException {
        String rtkInRedis = redisDao.getValues(user.getEmail());
        if (Objects.isNull(rtkInRedis)) throw new CustomException(ExceptionCode.DEFAULT_ERROR);
        Subject atkSubject = Subject.access(
                user.getId(),
                user.getEmail(),
                user.getUsername());
        String atk = createToken(atkSubject, atkLive);
        return new TokenResponseDTO(atk, null);
    }

    private String createToken(Subject subject, Long tokenLive) throws JsonProcessingException {
        String subjectStr = objectMapper.writeValueAsString(subject);
        Claims claims = Jwts.claims()
                .setSubject(subjectStr);
        Date date = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + tokenLive))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public Subject getSubject(String atk) throws JsonProcessingException {
        String subjectStr = Jwts.parser().setSigningKey(key).parseClaimsJws(atk).getBody().getSubject();
        return objectMapper.readValue(subjectStr, Subject.class);
    }
}
