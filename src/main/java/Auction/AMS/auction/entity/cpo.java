package Auction.AMS.auction.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class cpo {
    private Long id;
    private String bank;
    private Long amount;
    private String companyName;  // Add this field to hold the company name
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private int company;
    private int issuing_payment;

}
