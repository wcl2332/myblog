package com.wangcl.myblog.utils;

import com.wangcl.myblog.model.entity.User;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wangchenglong
 */
public class JWTUtils {
    private static final String JWTKEY = "123456!@###$$";

    public static String createToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("Name", user.getAccount());
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, JWTKEY)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));
        String token = jwtBuilder.compact();
        return token;
    }

    public static Map<String, Object> checkToken(String Token) {
        Jwt parse = Jwts.parser().setSigningKey(JWTKEY).parse(Token);
        return (Map<String, Object>) parse.getBody();
    }

    public static void main(String[] args) {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MjA4MTY4MTQwOSwiZXhwIjoxNjQ1NTQ1NTM2LCJpYXQiOjE2NDU0NTkxMzYsIk5hbWUiOiIxMzI4MjgwMjU4MCJ9._kelSDLxaFn2C9avKZbB40IlqYAGpjMs9NL11dlbJZr";
        Map<String,Object> map=checkToken(token);
        System.out.println(map.toString());
    }
}
