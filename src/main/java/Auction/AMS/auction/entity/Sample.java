package Auction.AMS.auction.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sample {
    private Long id;
    private Long companyId;
    private Long auctionId;
    private String sampleType;
    private BigDecimal price;
    private LocalDate date;
    private BigDecimal docPrice;
    private int merchantReturnable;
    private int returnStatus;
    private String companyName;
    private String auctionCode;

}
