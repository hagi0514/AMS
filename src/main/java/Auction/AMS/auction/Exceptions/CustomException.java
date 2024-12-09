package Auction.AMS.auction.Exceptions;

public class CustomException {

    public static class AuctionCodeDuplicationException extends RuntimeException {
        public AuctionCodeDuplicationException(String message) {
            super(message);
        }
    }

    public static class CompanyNameDuplicationException extends RuntimeException {
        public CompanyNameDuplicationException(String message) {
            super(message);
        }
    }
}
