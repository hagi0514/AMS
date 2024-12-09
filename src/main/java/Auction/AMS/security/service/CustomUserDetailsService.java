package Auction.AMS.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Auction.AMS.security.entity.Users;
import Auction.AMS.security.repository.UserRepository;

import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
   

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username)
                     .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    
        // Convert roles to Spring Security GrantedAuthority
        List<GrantedAuthority> authorities = user.getRoles().stream()
                                                 .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                                                 .collect(Collectors.toList());
    
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            authorities
        );
    }
    
    // Optional helper to retrieve Users directly
    public Users getUserByUsername(String username) {
        return userRepository.findByUsername(username)
               .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    
}
