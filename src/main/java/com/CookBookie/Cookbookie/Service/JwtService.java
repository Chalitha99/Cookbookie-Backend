package com.CookBookie.Cookbookie.Service;
import com.CookBookie.Cookbookie.Repository.TokenRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import com.CookBookie.Cookbookie.Model.User;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private final String SECRET_KEY = "ef4b79b0f04be7808680d077c519e6d1d858a88019ff6067e055a96e9e4931e5";

    private final TokenRepo tokenRepo;

    public JwtService(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    //3
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }
    //5
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //6
    public boolean isValid(String token, UserDetails user) {
        String username = extractUsername(token);

        boolean isvalidToken = tokenRepo
                .findByToken(token)
                .map(t -> !t.isLoggedOut())
                .orElse(false);

        return (username.equals(user.getUsername())) && !isTokenExpired(token) && isvalidToken;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //4
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    //////1
    public String generateToken(User user) {

        String token = Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24*60*60*60*1000))
                .signWith(getSignKey())
                .compact();

        return token;

    }
    //2
    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
