package Auction.AMS.security.configuration;

import Auction.AMS.security.entity.Users;
import Auction.AMS.security.entity.Role;
import Auction.AMS.security.repository.UserRepository;
import Auction.AMS.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public ApplicationRunner initializer() {
        return args -> {
            String adminUsername = "admin";
            String adminPassword = "admin123";  
            String adminRole = "ADMIN";

            // Fetch or create the ADMIN role
            Role role = roleRepository.findByName(adminRole)
                    .orElseGet(() -> {
                        // Create a new Role object and return it
                        Role newRole = new Role();
                        newRole.setName(adminRole);
                        return roleRepository.save(newRole);  // Save the new role
                    });

            // Check if admin user exists, if not, create and save
            if (userRepository.findByUsername(adminUsername).isEmpty()) {
                Users adminUser = new Users();
                adminUser.setUsername(adminUsername);
                adminUser.setPassword(new BCryptPasswordEncoder().encode(adminPassword));

                // Add the role to the user's roles set
                Set<Role> roles = new HashSet<>();
                roles.add(role);
                adminUser.setRoles(roles);

                // Save the admin user
                userRepository.save(adminUser);
            }
        };
    }
}
