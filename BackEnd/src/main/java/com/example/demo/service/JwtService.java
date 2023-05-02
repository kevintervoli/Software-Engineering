package com.example.demo.service;


import com.example.demo.entity.AuthenticationResponse;
import com.example.demo.entity.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "7234753778214125432A462D4A614E645267556B58703273357638792F423F45";
    public static final long JWT_AGENT_TOKEN_VALIDITY = 30 * 24 * 60 * 60;
    public static final long JWT_REFRESH_TOKEN_VALIDITY = 1 * 60 * 60;
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    public <V, K> String generateToken(String username, Role role) {
        return generateToken(new HashMap<>(),username, role);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            String username,
            Role role
    ){
        extraClaims.put("roleId", role.getId());
        extraClaims.put("roleName", role.getName());

        System.err.println((new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)));

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis() - 1))
                .setExpiration(new Date(System.currentTimeMillis() - 1 + 1000 * 60 * 60 * 24 * 7)) //one week from now
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return  extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public AuthenticationResponse decodeToken(String jwt){

        String[] parts = jwt.split("\\.");
        String encodedHeader = parts[0];
        String encodedPayload = parts[1];
        String signature = parts[2];

        // Decode the payload from base64 encoding
        byte[] decodedPayload = Base64.getDecoder().decode(encodedPayload);
        String payloadJson = new String(decodedPayload);

        // Verify the signature of the JWT
        Key key = getSignInKey();
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);

        // Extract the extra claims, username, and role information from the payload
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        int roleId = (int) claims.get("roleId");
        String roleName = (String) claims.get("roleName");
        String username = claims.getSubject();

        return new AuthenticationResponse(username, jwt, new Role(roleId, roleName));
    }
}
