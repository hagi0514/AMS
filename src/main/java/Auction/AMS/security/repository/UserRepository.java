package Auction.AMS.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Auction.AMS.security.entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    
}
