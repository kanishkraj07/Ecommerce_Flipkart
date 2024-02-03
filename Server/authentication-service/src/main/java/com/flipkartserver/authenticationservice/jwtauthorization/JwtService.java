package com.flipkartserver.authenticationservice.jwtauthorization;

import com.flipkartserver.authenticationservice.OtpAuthentication.CustomerDetailsRequest;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtService {

    private static final String SECRET_KEY = "4508b3d49df1ca6291b60b4f5afcd78aaf1ffcc9e3dcb1b1911574dab74a10d6";

    public String generateAuthToken(CustomerDetailsRequest customerDetailsRequest) {
        Map<String, Object> claims = new HashMap<>();
       return this.createToken(claims, customerDetailsRequest.getMobileNo(), customerDetailsRequest.getCustomerId());
    }

    public void validateAuthToken(String token) throws Exception {
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parse(token);
    }

    private String createToken(Map<String, Object> claims, long mobileNo, UUID customerId) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(Long.toString(mobileNo))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .claim("customerId", customerId)
                .compact();
    }



    private Key getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
