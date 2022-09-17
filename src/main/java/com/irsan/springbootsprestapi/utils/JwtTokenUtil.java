package com.irsan.springbootsprestapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irsan.springbootsprestapi.model.MemberPerpusData;
import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */

@Component
public class JwtTokenUtil implements Serializable {

    @Resource
    private ObjectMapper objectMapper;

    private static final long serialVersionUID = -4826425677328980278L;
    public static final long JWT_TOKEN_VALIDITY = 24;
    private static final String SECRET = "techgeeknext";

    private String doGenerateToken(Map<String, Object> claims, MemberPerpusData memberPerpusData) {
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(objectMapper.writeValueAsString(memberPerpusData))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + (TimeUnit.HOURS.toMillis(JWT_TOKEN_VALIDITY))))
                    .signWith(SignatureAlgorithm.HS256, SECRET)
                    .compressWith(CompressionCodecs.GZIP)
                    .compact();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String generateToken(MemberPerpusData memberPerpusData) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, memberPerpusData);
    }

    public MemberPerpusData extractToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            if (new Date().after(claims.getBody().getExpiration())) {
                return null;
            }
            return objectMapper.readValue(claims.getBody().getSubject(), MemberPerpusData.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        MemberPerpusData memberPerpusData = extractToken(token);
        String username = memberPerpusData.getUsername();
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
