package Auction.AMS.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Auction.AMS.auction.entity.cpo;
import Auction.AMS.auction.mapper.cpoMapper;

import java.util.List;


@Service
public class cpoService {

    @Autowired
    private cpoMapper cpomapper;


    public void addCpo(cpo cpoInfo){
    cpomapper.addCpoInfo(cpoInfo);
    }

    public List<cpo> cpoList(){
        // System.out.println("companyName"+cpomapper.cpoList().get(id));
        return cpomapper.cpoList();
    }

    public void updateCpo(cpo cpo,Long id){
       cpomapper.updateCpo(cpo ,id);
    }
   
    public void deleteCpo(cpo cpo,Long id) {
        cpomapper.deleteCpo(cpo,id);  // Use id directly
    }
    public cpo getCpoById(Long id){
        return cpomapper.getCpoById(id);
    }
}
