package Auction.AMS.auction.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.*;

import Auction.AMS.auction.entity.Auction;
import Auction.AMS.security.entity.Users;

@Mapper
public interface AuctionMapper {
        @Insert("insert into auction(company_id,start_date,end_date,auction_code,sample_status,user_id)values(#{companyId},#{startDate},#{endDate},#{auctionCode},#{sampleStatus},#{userId})")
        public void saveAuction(Auction auction);

        @Select("SELECT COUNT(*) FROM auction WHERE company_id = #{companyId} AND auction_code = #{auctionCode}")
        public boolean isAuctionCodeExist(Long companyId, String auctionCode);

        @Select("SELECT auction.* ,company.name AS companyName" +
                        " from auction" +
                        " JOIN company ON auction.company_id=company.id")
        @Results({
                        @Result(property = "id", column = "id"),
                        @Result(property = "companyId", column = "company_id"), // Map the companyName field
                        @Result(property = "startDate", column = "start_date"),
                        @Result(property = "endDate", column = "end_date"),
                        @Result(property = "sampleStatus", column = "sample_status"),
                        @Result(property = "auctionCode", column = "auction_code"),
                        @Result(property = "companyName", column = "companyName"), // Map the companyName field
                        @Result(property = "userId", column = "user_id")
        })
        public List<Auction> auctionList();

        @Select("SELECT auction.*, company.name AS companyName " +
                        "FROM auction " +
                        "JOIN company ON auction.company_id = company.id " +
                        "WHERE auction.id = #{id}")
        @Results({
                        @Result(property = "id", column = "id"),
                        @Result(property = "companyId", column = "company_id"),
                        @Result(property = "companyName", column = "companyName"), // Mapping for company name
                        @Result(property = "startDate", column = "start_date"),
                        @Result(property = "endDate", column = "end_date"),
                        @Result(property = "sampleStatus", column = "sample_status"),
                        @Result(property = "auctionCode", column = "auction_code"),
                        @Result(property = "user", javaType = Users.class, column = "user_id", one = @One(select = "Auction.AMS.auction.cpo.mapper.UserMapper.findById"))
        })
        public Auction getAuctionById(Long id);

        @Update("update auction set company_id=#{companyId},start_date=#{startDate},end_date=#{endDate}, auction_code=#{auctionCode},sample_status=#{sampleStatus} where id=#{id}")
        public void updateAuction(Auction auction);

        @Select("SELECT auction.*, company.name AS companyName " +
                        "FROM auction " +
                        "JOIN company ON auction.company_id = company.id " +
                        "WHERE auction.company_id = #{id}")
        @Results({
                        @Result(property = "id", column = "id"),
                        @Result(property = "companyId", column = "company_id"),
                        @Result(property = "companyName", column = "companyName"), // Mapping for company name
                        @Result(property = "startDate", column = "start_date"),
                        @Result(property = "endDate", column = "end_date"),
                        @Result(property = "sampleStatus", column = "sample_status"),
                        @Result(property = "auctionCode", column = "auction_code"),
                        @Result(property = "user", javaType = Users.class, column = "user_id", one = @One(select = "Auction.AMS.auction.cpo.mapper.UserMapper.findById"))
        })
        public List<Auction> getAuctionByCompanyId(Long id);

        @Select("SELECT auction.*, company.name AS companyName " +
                        "FROM auction " +
                        "JOIN company ON auction.company_id = company.id " +
                        "WHERE end_date BETWEEN #{startDate} AND #{endDate}")
        @Results({
                        @Result(property = "id", column = "id"),
                        @Result(property = "companyId", column = "company_id"),
                        @Result(property = "companyName", column = "companyName"), // Mapping for company name
                        @Result(property = "startDate", column = "start_date"),
                        @Result(property = "endDate", column = "end_date"),
                        @Result(property = "sampleStatus", column = "sample_status"),
                        @Result(property = "auctionCode", column = "auction_code"),
                        @Result(property = "user", javaType = Users.class, column = "user_id", one = @One(select = "Auction.AMS.auction.cpo.mapper.UserMapper.findById"))

        })
        List<Auction> findByAuctionDateBetween(@Param("startDate") LocalDateTime startDate,
                        @Param("endDate") LocalDateTime endDate);

}