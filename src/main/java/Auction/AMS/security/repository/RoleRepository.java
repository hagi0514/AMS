package Auction.AMS.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Auction.AMS.security.entity.Role;

import java.util.Optional;
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}