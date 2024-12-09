package Auction.AMS.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Configuration
@EnableJpaRepositories(basePackages = "Auction.AMS.security.repository") // Point to the package where your repositories are
@EntityScan(basePackages = "Auction.AMS.security.entity") // Point to the package where your entities are
public class JpaConfig {
    // This class will configure the necessary beans for JPA to work
}
