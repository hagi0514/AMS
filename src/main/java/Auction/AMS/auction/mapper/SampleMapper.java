package Auction.AMS.auction.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import Auction.AMS.auction.entity.Sample;


@Mapper
public interface SampleMapper {

    @Insert("insert into sample(company_id,auction_id,sample_type,price,date,doc_price,merchant_returnable,return_status)values(#{companyId},#{auctionId},#{sampleType},#{price},CURRENT_TIMESTAMP,#{docPrice},#{merchantReturnable},#{returnStatus})")
     void addSample(Sample sample);

    @Select("SELECT sample.*, company.name AS companyName, auction.auction_code AS auctionCode " +
            "FROM sample " +
            "JOIN company ON sample.company_id = company.id " +
            "JOIN auction ON sample.auction_id = auction.id ")
    @Results({
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "auctionId", column = "auction_id"),
            @Result(property = "sampleType", column = "sample_type"),
            @Result(property = "price", column = "price"),
            @Result(property = "docPrice", column = "doc_price"),
            @Result(property = "merchantReturnable", column = "merchant_returnable"),
            @Result(property = "returnStatus", column = "return_status"),
            @Result(property = "companyName", column = "companyName"),
            @Result(property = "auctionCode", column = "auctionCode"),
    })
    public List<Sample> sampleList();

    @Update("update sample set company_id=#{companyId},auction_id=#{auctionId},sample_type=#{sampleType},price=#{price},doc_price=#{docPrice},merchant_returnable=#{merchantReturnable},return_status=#{returnStatus} where id=#{id}")
    public void updateSample(Sample sample);

    @Select("SELECT sample.*, company.name AS companyName, auction.auction_code AS auctionCode " +
            "FROM sample " +
            "JOIN company ON sample.company_id = company.id " +
            "JOIN auction ON sample.auction_id = auction.id " +
            "WHERE sample.id = #{id}")
    @Results({
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "auctionId", column = "auction_id"),
            @Result(property = "sampleType", column = "sample_type"),
            @Result(property = "price", column = "price"),
            @Result(property = "docPrice", column = "doc_price"),
            @Result(property = "merchantReturnable", column = "merchant_returnable"),
            @Result(property = "returnStatus", column = "return_status"),
            @Result(property = "companyName", column = "companyName"),
            @Result(property = "auctionCode", column = "auctionCode"),
    })
    public Sample getSampleById(Long id);
}
