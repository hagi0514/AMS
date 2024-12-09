package Auction.AMS.auction.mapper;


import org.apache.ibatis.annotations.*;

import Auction.AMS.auction.entity.cpo;

import java.util.List;



@Mapper
public interface cpoMapper {

    @Insert("insert into cpo(issuing_payment,amount,company,bank,date,status) values(#{issuing_payment},#{amount},#{company},#{bank},CURRENT_TIMESTAMP,1)")
    public void addCpoInfo(cpo cpoInfo);

    @Select("SELECT cpo.*, company.name AS companyName " +
    "FROM cpo " +
    "JOIN company ON cpo.company = company.id " +
    "WHERE cpo.status = 1")
@Results({
@Result(property = "id", column = "id"),
@Result(property = "company", column = "company"),
@Result(property = "companyName", column = "companyName"), // Map the companyName field
// Map other fields here as necessary
})
public List<cpo> cpoList();

    @Update("UPDATE cpo SET issuing_payment = #{cpo.issuing_payment}, amount = #{cpo.amount}, company = #{cpo.company}, bank = #{cpo.bank} WHERE id = #{id}")
    public int updateCpo(@Param("cpo") cpo cpo, @Param("id") Long id);

    @Update("update cpo set status= 0 where id=#{id}")
    public void deleteCpo(cpo cpo,Long id);
    @Select("select * from cpo where id = #{id}")
     public cpo getCpoById(Long id);
       
}