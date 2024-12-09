package Auction.AMS.security;

import java.util.List;

public class JwtResponse {
    private String token;
    private List<String> roles;
    private Long userId;
    private String refreshToken;


    public JwtResponse(String token, List<String> roles,Long userId,String refreshToken) {
        this.token = token;
        this.roles = roles;
        this.userId = userId;
        this.refreshToken=refreshToken;

    }

    public String getToken() {
        return token;
    }
    public String getRefreshToken(){
        return refreshToken;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public void setRefreshToken(String refreshToken){
        this.refreshToken= refreshToken;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
