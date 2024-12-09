package Auction.AMS.auction.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Auction.AMS.auction.mapper.UserMapper;
import Auction.AMS.security.PasswordUtil;
import Auction.AMS.security.entity.Role;
import Auction.AMS.security.entity.Users;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void registerUser(Users user, Long roleId) {
        // Use static method from PasswordUtil
        user.setPassword(PasswordUtil.encodePassword(user.getPassword()));

        userMapper.saveUser(user);
        userMapper.saveUserRole(user.getId(), roleId);
    }

    public boolean changePassword(Long userId, String oldPassword, String newPassword) {
        Users user = userMapper.findById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
    
        if (!PasswordUtil.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }
    
        String encodedNewPassword = PasswordUtil.encodePassword(newPassword);
        int rowsAffected = userMapper.changePassword(userId, encodedNewPassword);
        return rowsAffected > 0;
    }
    

    public List<Role> allRoles() {
        return userMapper.allRoles();
    }

    public List<String> userExists() {
        return userMapper.userExists();
    }

    public Users findById(Long id) {
        return userMapper.findById(id);
    }

    public void updateProfileImage(String profileImage, Long userId) {
        System.out.println("Inside updateProfileImage method");
        System.out.println("userId: " + userId);
        System.out.println("profileImage: " + profileImage);
    
        // Check if user exists
        Users user = userMapper.findById(userId);
        if (user == null) {
            System.out.println("User not found with ID: " + userId);
            throw new RuntimeException("User not found");
        }
    
        // Update the profile image
        if (profileImage == null || profileImage.trim().isEmpty()) {
            // If no profile image is provided, set the default
            user.setProfileImage("/assets/img/new_logo.png");
            System.out.println("Setting default profile image for userId: " + userId);
        } else {
            // Update the user's profile image with the new one
            user.setProfileImage(profileImage);
            System.out.println("Updated profile image for userId: " + userId);
        }
    
        // Save changes to the database
        userMapper.updateProfilePic(user.getProfileImage(), userId);
    }
    
}
