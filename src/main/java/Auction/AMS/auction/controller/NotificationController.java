package Auction.AMS.auction.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Auction.AMS.auction.entity.Notification;
import Auction.AMS.auction.service.NotificationService;
import jakarta.servlet.http.HttpServletRequest;
import payload.endpoint.Endpoint;


@CrossOrigin(origins = Endpoint.URL, maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("api/")
public class NotificationController {
@Autowired
private NotificationService notificationService;

  @PutMapping("/dismisNotification/{id}")
  public ResponseEntity<HttpStatus>dismisNotification(@PathVariable("id") Long id){
    notificationService.dismisNotification(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
@GetMapping("/notification")
public ResponseEntity<?> getNotifications(@RequestHeader("Authorization") String authorization,HttpServletRequest request) {
    // System.out.println("Received Authorization Header: " + authorization);
    Long userId = (Long) request.getAttribute("userId");

    if (userId == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    List<Notification> notifications = notificationService.findByUserId(userId);
    // System.out.println("userId" +userId);
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime fourDaysLater = now.plusDays(4);
    notificationService.sendAuctionReminders(now, fourDaysLater);
    // System.out.println("Notifications fetched: " + notifications);
    return ResponseEntity.ok(notifications);
}

}
