package Auction.AMS.auction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Auction.AMS.auction.entity.Company;
import Auction.AMS.auction.mapper.CompanyMapper;



@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    public void saveCompany(Company company){
    companyMapper.saveCompany(company);
      }
      public List<Company>companyList(){
        return companyMapper.companyList();
      }

    public void updateCompany(Company company){
      System.out.println("Update sertvice is called"+company);

        companyMapper.updateCompany(company);
    }
    public void deleteCompany(Long id){
        companyMapper.deleteCompany(id);
    }
    public Company getCompanyById(Long id){
        return companyMapper.getCompanyById(id);
    }

}
