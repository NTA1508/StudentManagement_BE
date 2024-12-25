package com.school.student_mg.services;

import com.school.student_mg.models.Lecturer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Create Sign Key
    public SecretKey getSignKey(){
        String SECRET_KEY = "084e86e186aa512cfef70512fee162ac2a8da1a93dbc236fdccd4970f88ee197";
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Giải mã và trích xuất tất cả các claims (dữ liệu được mã hóa) từ một JWT
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                    .verifyWith(getSignKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }

    //trích xuất một claim cụ thể từ một JWT bằng cách sử dụng functional reference
    public <T> T extractClaim(String token, Function<Claims, T> resolver){
        Claims claims = extractAllClaims(token);
        return  resolver.apply(claims);
    }

    //Trích xuất thông tin email từ một JWT bằng cách sử dụng Claims
    public String extractEmail(String token){
        return  extractClaim(token, Claims::getSubject);
    }

    //trích xuất expiration date từ JWT
    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    // Kiểm tra xem một JWT đã hết hạn hay chưa.
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //Kiểm tra tính hợp lệ của một JWT dựa trên thông tin người dùng và thời hạn hiệu lực của token
    public boolean isValid(String token, UserDetails user){
        String email = extractEmail(token);
        return (email.equals(user.getUsername())) && !isTokenExpired(token);
    }

    //Tạo một JWT dựa trên thông tin người dùng được cung cấp.
    public String generateToken(Lecturer lecturer){
        return Jwts.builder()
                .subject(lecturer.getEmail()).claims(Map.of("id", lecturer.getlId(), "role", lecturer.getRoles()))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .signWith(getSignKey())
                .compact();
    }

    //trích xuất ID người dùng từ một JWT
    public String extractUserid(String token){
        Claims claims = extractAllClaims(token);
        return (String) claims.get(("id"));
    }
}
