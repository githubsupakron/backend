package com.scb.book.backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.scb.book.backend.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {

    @Value("${app.token.secret}")
    private String secret;
    @Value("${app.token.issuer}")
    private String issuer;

    public String tokenize(UserEntity user){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,5);
        Date eExpiresAt = calendar.getTime();
        String userId = user.getId().toString();
        String token = JWT.create()
                .withIssuer(issuer)
                .withClaim("principal",userId)
                .withClaim("role","USER")
                .withExpiresAt(eExpiresAt)
                .sign(algorithm());

        return token;
    }

    public DecodedJWT verifyToken(String token){

        try {

            JWTVerifier verify = JWT.require(algorithm())
                    .withIssuer(issuer)
                    .build();

            DecodedJWT jwt = verify.verify(token);
            return jwt;

        }catch (Exception e){
            return null;
        }

    }


    public Algorithm algorithm(){
        return Algorithm.HMAC256(secret);
    }
}
