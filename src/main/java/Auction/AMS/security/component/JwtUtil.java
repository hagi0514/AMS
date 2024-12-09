package Auction.AMS.security.component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationTime;

    @Value("${jwt.refreshExpiration}")
    private long refreshExpirationTime;

    private JwtParser jwtParser;

    // Constructor or init block to configure JwtParser
    @PostConstruct
    public void initializeJwtParser() {
        if (secret == null || secret.isEmpty()) {
            throw new IllegalStateException("JWT secret key is not configured!");
        }
        System.out.println("Initializing JwtParser with secret: " + secret);
        this.jwtParser = Jwts.parserBuilder()
                .setSigningKey(secret)
                .setAllowedClockSkewSeconds(30) // Allow 30 seconds of clock skew
                .build();
    }
    

    // Generate token with multiple roles
    public String generateToken(String username, List<String> roles, Long userId) {
        String rolesString = String.join(",", roles); // Convert roles to a comma-separated string

        return Jwts.builder()
                .setSubject(username)                 // Set the username as the subject
                .claim("roles", rolesString)          // Add roles as a claim
                .claim("userId", userId)              // Add userId as a claim
                .setIssuedAt(new Date())              // Set issued date
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationTime)) // Set expiration time
                .signWith(SignatureAlgorithm.HS256, secret) // Sign the token with the secret key
                .compact();                           // Build the token
    }

    // Generate refresh token

    public String generateRefreshToken(String username, List<String> roles, Long userId) {
        String rolesAsString = String.join(",", roles);

        String refreshToken = Jwts.builder()
        // Convert roles to a comma-separated string
            .setSubject(username)
            .claim("roles", rolesAsString) // Store as String
            .claim("userId", userId)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact();;
        // System.out.println("Generated Refresh Token from jwt: " + refreshToken);
        return refreshToken;
    }

    // Extract claims using the updated parser
  public Claims getClaims(String token) {
    try {
        return jwtParser.parseClaimsJws(token).getBody();
    } catch (ExpiredJwtException e) {
        System.err.println("Token is expired: " + e.getMessage());
        throw e; // Re-throw or handle as per your logic
    } catch (Exception e) {
        System.err.println("Error parsing token: " + e.getMessage());
        throw new RuntimeException("Invalid token");
    }
}


    // Extract username from token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract roles from token
    public List<String> extractRoles(String token) {
        String rolesString = extractAllClaims(token).get("roles", String.class);
        return List.of(rolesString.split(",")); // Split roles by comma
    }

    // Extract userId from token
    public Long extractUserId(String token) {
        return extractClaim(token, claims -> claims.get("userId", Long.class));
    }

    // Validate token
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
 
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }
}
