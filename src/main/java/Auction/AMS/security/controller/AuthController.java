package Auction.AMS.security.controller;

import Auction.AMS.security.service.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import Auction.AMS.security.entity.AuthRequest;
import Auction.AMS.security.entity.Users;
import Auction.AMS.security.JwtResponse;
import Auction.AMS.security.RefreshTokenRequest;
import Auction.AMS.security.component.JwtUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    // Login endpoint to generate JWT token
    @PostMapping("/login")
    public ResponseEntity<?> createJwtToken(@RequestBody AuthRequest authRequest) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            
            // Get the authenticated user
            User user = (User) authentication.getPrincipal();

            // Retrieve all roles of the user
            String username = user.getUsername();
            List<String> roles = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            
            // Fetch custom user details from the database
            Users customUser = userDetailsService.getUserByUsername(username);
            Long userId = customUser.getId();

            // Generate the JWT token using username, roles, and userId
            String token = jwtUtil.generateToken(user.getUsername(), roles, userId);
            String refreshToken = jwtUtil.generateRefreshToken(user.getUsername(), roles, userId); // 7 days expiry
            System.out.println("Generated refresh token: " + refreshToken);
            System.out.println("Generated Access token: " + token);

            // Return the token in the response body
            return ResponseEntity.ok(new JwtResponse(token, roles, userId, refreshToken));

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    // Endpoint to refresh JWT token
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshJwtToken(@RequestBody RefreshTokenRequest request) {
        if (request == null || request.getRefreshToken() == null) {
            return ResponseEntity.badRequest().body("Invalid request: Refresh token is missing.");
        }
        
        String refreshToken = request.getRefreshToken();
        try {
            // Extract data from refresh token
            String username = jwtUtil.extractUsername(refreshToken);
            List<String> roles = jwtUtil.extractRoles(refreshToken);
            Long userId = jwtUtil.extractUserId(refreshToken);
    
            // Check if the refresh token is expired
            if (jwtUtil.isTokenExpired(refreshToken)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token expired.");
            }

            // Generate new access token using extracted data
            String newToken = jwtUtil.generateToken(username, roles, userId);
            return ResponseEntity.ok(new JwtResponse(newToken, roles, userId, refreshToken));
        } catch (ExpiredJwtException ex) {
            // Handle expired refresh token case
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token expired.");
        } catch (Exception ex) {
            // Handle other errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error refreshing token: " + ex.getMessage());
        }
    }

}
