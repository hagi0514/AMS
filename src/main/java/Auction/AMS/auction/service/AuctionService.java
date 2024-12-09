package Auction.AMS.auction.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Auction.AMS.auction.Exceptions.CustomException;
import Auction.AMS.auction.entity.Auction;
import Auction.AMS.auction.mapper.AuctionMapper;


@Service
public class AuctionService {
    @Autowired

    private AuctionMapper auctionMapper;

    public void saveAuction(Auction auction) {
    if (auctionMapper.isAuctionCodeExist(auction.getCompanyId(), auction.getAuctionCode())) {
        throw new CustomException.AuctionCodeDuplicationException("Auction code already exists for this company.");
    }
    auctionMapper.saveAuction(auction);
}
    
    

    public List<Auction>auctionList(){
        return auctionMapper.auctionList();
    }

    public Auction getAuctionById(Long id){
        return auctionMapper.getAuctionById(id);
    }

    public void updateAuction(Auction auction){
        auctionMapper.updateAuction(auction);
    }

    public List<Auction> getAuctionByCompanyId(Long companyId){
        return auctionMapper.getAuctionByCompanyId(companyId);
    }
 
    

}
