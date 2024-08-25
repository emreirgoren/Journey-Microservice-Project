package com.patika.basket_service.utils;


import com.patika.basket_service.model.enums.Gender;
import com.patika.basket_service.model.enums.UserType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.function.Function;

@Component
public class JwtUtils {

    public static final String SECRET = "4f4d5a5b6c7d8e9f0a1b2c3d4e5f6a7b8c9d0e1f2a3b4c5d6e7f8g9h0i1j2k3l4m5n6o7p8q9r0s1t2u3v4w5x6y7z8a9b0c1d2e3f4g5h6i7j8k9l0m1n2o3p4q5r6s7t8u9v0w1x2y3z4a5b6c7d8e9f0";

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Gender extractGender(String token){
        return Gender.valueOf(extractAllClaims(token).get("gender",String.class));
    }

    public UserType extractUserType(String token){
        token = token.substring(7);
        return UserType.valueOf(extractAllClaims(token).get("userType",String.class));
    }

    public String extractPhoneNumber(String token){
        return extractAllClaims(token).get("phoneNumber",String.class);
    }


    public String extractEmail2(String token){
        String token1 = token.substring(7);
        return extractAllClaims(token1).get("email",String.class);
    }


}
