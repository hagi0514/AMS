package Auction.AMS.security;

public class RefreshTokenRequest {
    private String refreshToken;

    // Default constructor (needed for deserialization)
    public RefreshTokenRequest() {}

    // Getter
    public String getRefreshToken() {
        return refreshToken;
    }

    // Setter
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    // @Override
    // public String toString() {
    //     return "RefreshTokenRequest{refreshToken='" + refreshToken + "'}";
    // }
}
