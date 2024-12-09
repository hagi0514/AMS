package Auction.AMS.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority {  // Define Authority entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // e.g., "READ_PRIVILEGES", "WRITE_PRIVILEGES"

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
