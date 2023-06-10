package com.thi.bt.knn.config;


import com.thi.bt.knn.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

@Component
public class TokenHelper {

    private static final String USER_ID = "ui";
    private static final String USER_NAME = "uname";
    private static final String token_signing_key="me2OiYnkuktbtggjmo8dOayJkCHJv7vu35uEKAAPE0Aw9dtN4zCdltxtSrOh9ca5DrPCwXK1r5LZ479reQ3tbg";
    private String APP_NAME = "knn";
    private int EXPIRES_IN = 86400;
    byte[] secret = token_signing_key.getBytes();

    public String getUsernameFromToken(String token) {
        String username = "";
        try {
            final Claims claims = this.getClaimsFromToken(token);

            Date validTo = claims.getExpiration();
            Date requestTime = new Date();

            if (validTo.after(requestTime)) {
                username = claims.getSubject();
            }

        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public String generateToken(String usernameLogin, User user, String session) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        String jws = Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(usernameLogin)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .claim(USER_ID, user.getId())
                .claim(USER_NAME, user.getUsername())
                .compact();
        return jws;
    }

    public JwtAuthDto getJWTInfor(String authToken) {
        Claims body = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(authToken)
                .getBody();
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setUi(body.get(USER_ID, Integer.class));
        jwtAuthDto.setUname(body.get(USER_NAME, String.class));
        return jwtAuthDto;
    }

    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }



    public Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + EXPIRES_IN * 1000);
    }

}
