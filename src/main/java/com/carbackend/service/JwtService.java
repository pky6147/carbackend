package com.carbackend.service;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    static final String PREFIX = "Bearer ";
    //서버와 클라이언트가 주고 받는 토큰은 HTTP 헤더에 Authorization 헤더 값에 저장됨
    //==> Authorization Bearer <토큰값> 예)asdiadsgASDASD
    static final long EXPIRATIONTIME = 24*60*60*1000;
    static final Key SIGNING_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SIGNING_KEY)
                .compact();
    }

    //JWT를 받아서 username(id)을 반환
    public String parseToken(HttpServletRequest request) {
        //요청헤더에서 Authorization 헤더값 가져옴 ==> Authorization Bearer <토큰값>
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith(PREFIX)) {
            JwtParser parser= Jwts.parserBuilder()
                    .setSigningKey(SIGNING_KEY)
                    .build();
            String username = parser.parseClaimsJws(header.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();
            if(username!=null){
                return username;
            }
        }
        return null;
    }

}
