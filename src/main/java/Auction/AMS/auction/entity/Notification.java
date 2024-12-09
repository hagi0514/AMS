package Auction.AMS.auction.entity;


import java.time.LocalDateTime;

import Auction.AMS.security.entity.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id") // Maps to the Users table
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "auction_id") // Maps to the Auction table
    private Long auctionId;

    private String message;
    private int status;

    private LocalDateTime createdAt;
}

