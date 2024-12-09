package Auction.AMS.auction.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import Auction.AMS.auction.Exceptions.CustomException.CompanyNameDuplicationException;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateKeyException(DuplicateKeyException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
    
    @ExceptionHandler(CustomException.AuctionCodeDuplicationException.class)
    public ResponseEntity<Map<String, String>> handleAuctionCodeDuplicationException(CustomException.AuctionCodeDuplicationException ex) {
        System.out.println("AuctionCodeDuplicationException handled: " + ex.getMessage());
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    

    
    
    

    @ExceptionHandler(CompanyNameDuplicationException.class)
    public  ResponseEntity<Object>handleCompanyNameDuplicationException(CompanyNameDuplicationException ex){
        Map<String,String> response=new HashMap<>();
        response.put("message", ex.getMessage()); // Custom message for auction code duplication
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "An unexpected error occurred: " + ex.getMessage());
        
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
