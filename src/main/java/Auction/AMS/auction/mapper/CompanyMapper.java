package Auction.AMS.auction.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import Auction.AMS.auction.entity.Company;



@Mapper
public interface CompanyMapper {

    @Select("insert into company (name,status)values(#{name},1)")
    public void saveCompany(Company company);

    @Select("select * from company where status=1")
    public List<Company>companyList();

    @Update("update company set name=#{company.name} where id=#{company.id}")
    public void updateCompany(@Param("company") Company company);

    @Update("update company set status=0 where id=#{id}")
    public void deleteCompany(@Param("id")Long id);

    @Select("select * from company where id =#{id}")
    public Company getCompanyById(Long id);

}
