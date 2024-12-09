package Auction.AMS.auction.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import Auction.AMS.auction.entity.Notification;

@Mapper
public interface NotificationMapper {
    @Select("SELECT * FROM notifications WHERE user_id = #{userId} AND status=1")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "userId", column = "user_id"),
        @Result(property="auctionId", column = "auction_id"),
        @Result(property="createdAt",column = "created_at")
    })
        
    List<Notification> findByUserId(@Param("userId") Long userId);

    @Update("UPDATE notifications SET status = 0 WHERE id = #{id}")
     public void dismisNotification(@Param("id") Long notificationId);

    @Insert("INSERT INTO notifications (user_id, auction_id, status, created_at, message) VALUES (#{userId}, #{auctionId}, 1, CURRENT_TIMESTAMP, #{message})")
    void save(Notification notification);

    @Select("Select * from notifications where user_id=#{userId} AND auction_id=#{auctionId} AND message=#{message}")
    public Notification findByUserIdAndAuctionIdAndMessage(Long userId,Long auctionId,String message);


}

