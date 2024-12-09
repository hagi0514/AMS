package Auction.AMS.auction.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Auction.AMS.auction.entity.Auction;
import Auction.AMS.auction.service.AuctionService;
import Auction.AMS.auction.service.NotificationService;
import payload.endpoint.Endpoint;

@CrossOrigin(origins = Endpoint.URL, maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("api/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/saveAuction")
    public ResponseEntity<String> saveAuction(@RequestBody Auction auction) {
        auctionService.saveAuction(auction);
        return new ResponseEntity<>("Auction saved successfully", HttpStatus.CREATED);
    }

    @GetMapping("/auctionList")
    public ResponseEntity<List<Auction>> auctionList() {
        try {
            auctionService.auctionList();
            return new ResponseEntity<>(auctionService.auctionList(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAuctionById/{id}")
    public ResponseEntity<Auction> getAuctionById(@PathVariable("id") Long id) {
        Auction auctionId = auctionService.getAuctionById(id);
        return new ResponseEntity<>(auctionId, HttpStatus.OK);

    }

    @PutMapping("/updateAuction/{id}")
    public ResponseEntity<HttpStatus> updateAuction(@PathVariable("id") Long id, @RequestBody Auction auction) {
        try {
            Auction exisitingAuction = auctionService.getAuctionById(auction.getId());
            if (exisitingAuction != null) {
                auctionService.updateAuction(auction);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // If auction not found
            }

        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getAuctionsByCompanyId/{companyId}")
    public List<Auction> getAuctionsByCompanyId(@PathVariable Long companyId) {
        return auctionService.getAuctionByCompanyId(companyId);
    }

    // @GetMapping("/notification")
    // public ResponseEntity<List<Auction>> getUpcomingAuctions() {
    // LocalDateTime now = LocalDateTime.now();
    // LocalDateTime fourDaysLater = now.plusDays(4);
    // List<Auction> upcomingAuctions = auctionService.findAuctionsBetween(now,
    // fourDaysLater);
    // System.out.println("notification"+ upcomingAuctions);
    // return ResponseEntity.ok(upcomingAuctions);
    // }

    // @GetMapping("/notification")
    // public ResponseEntity<String> sendAuctionNotifications() {
    // LocalDateTime now = LocalDateTime.now();
    // LocalDateTime fourDaysLater = now.plusDays(4);

    // notificationService.sendAuctionReminders(now, fourDaysLater);
    // System.out.println("notification from auction controller " + "Notifications
    // sent for auctions ending in 4 days.");

    // return ResponseEntity.ok("Notifications sent for auctions ending in 4
    // days.");
    // }
}