package Auction.AMS.auction.entity;
import java.sql.Timestamp;
import java.time.LocalDate;

import Auction.AMS.security.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long companyId;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Users user;
    private Long userId;

    // @Pattern(regexp = "^[a-z0-9_-]+$", message = "Auction code must contain only lowercase letters, numbers, and allowed special characters (_ or -).")
    private String auctionCode;

    private int sampleStatus;
    private String companyName;

    // Setter for auctionCode with validation and lowercase conversion
    // public void setAuctionCode(String auctionCode) {
    //     if (auctionCode != null && auctionCode.matches("^[a-z0-9_/\\-]+$")) {
    //         this.auctionCode = auctionCode.toLowerCase();
    //     } else {
    //         throw new IllegalArgumentException("Auction code must contain only lowercase letters, numbers, and allowed special characters (_ or -).");
    //     }
    // }
}