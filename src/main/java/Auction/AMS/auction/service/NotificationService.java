package Auction.AMS.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import Auction.AMS.auction.entity.Auction;
import Auction.AMS.auction.entity.Notification;
import Auction.AMS.auction.mapper.AuctionMapper;
import Auction.AMS.auction.mapper.NotificationMapper;
import Auction.AMS.security.entity.Users;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper; // To interact with the database
    @Autowired
    private EmailService emailService;
    @Autowired
    private AuctionMapper auctionMapper; // To get auctions
    // Schedule this task to run daily at 9 AM (cron expression)

    @Scheduled(cron = "0 0 9 * * ?")
    public void sendAuctionReminders(LocalDateTime startDate, LocalDateTime endDate) {
        List<Auction> upcomingAuctions = auctionMapper.findByAuctionDateBetween(startDate, endDate);

        if (upcomingAuctions.isEmpty()) {
            // System.out.println("No auctions found for the given date range.");
            return;
        }

        for (Auction auction : upcomingAuctions) {
            Users user = auction.getUser(); // Ensure this does not return null
            if (user == null) {
                System.out.println("User is null for auction: " + auction.getId());
                System.out.println(auction.getUser());
                continue;
            }

            String message = "Auction of " + auction.getCompanyName() + " with Auction Code: "
                    + auction.getAuctionCode() + " Ending on " + auction.getEndDate();
            System.out.println("Sending notification to user: " + user.getUsername());

            Notification existingNotification = notificationMapper.findByUserIdAndAuctionIdAndMessage(
                    user.getId(), auction.getId(), message);

            // Send email notification
            // emailService.sendEmail(user.getEmail(), "Auction Ending Soon!", message);

            if (existingNotification == null) {
                Notification notification = new Notification();
                notification.setUserId(user.getId());
                notification.setAuctionId(auction.getId());
                notification.setMessage(message);
                notification.setStatus(1); // Status: New notification
                notification.setCreatedAt(LocalDateTime.now());

                notificationMapper.save(notification);
                System.out.println("Notification saved for user: " + user.getUsername());
            } else {
                System.out.println("Notification already exists for user: " + user.getUsername());
            }

        }
    }

    @Scheduled(cron = "0 0 9 * * ?")
    public void scheduleDailyAuctionReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fourDaysLater = now.plusDays(4);
        sendAuctionReminders(now, fourDaysLater);
    }

    // Method to update notification status when viewed or dismissed
    public void dismisNotification(Long notificationId) {
       notificationMapper.dismisNotification(notificationId);
        
    }
    public List<Notification>findByUserId(Long userId){
        return notificationMapper.findByUserId(userId);
    }

   
}
