// package Auction.AMS.auction.cpo.Repository;

// import java.util.List;

// import org.apache.catalina.User;
// import org.springframework.data.jpa.repository.JpaRepository;

// import Auction.AMS.auction.cpo.entity.Notification;

// public interface NotificationRepository extends JpaRepository<Notification, Integer> {
//     List<Notification> findByUserAndStatusNot(User user, int status);  // Get notifications for a user that are not dismissed
// }