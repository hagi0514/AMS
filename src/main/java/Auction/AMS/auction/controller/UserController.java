package Auction.AMS.auction.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import Auction.AMS.auction.service.UserService;
import Auction.AMS.security.entity.Role;
import Auction.AMS.security.entity.Users;
import payload.endpoint.Endpoint;


@CrossOrigin(origins = Endpoint.URL, maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("api/auth/user")
public class UserController {

   @Autowired
   private UserService userService;

   @PostMapping("/register")
   public ResponseEntity<HttpStatus> registerUsers(@RequestBody Users user, @RequestParam Long roleId) {

      userService.registerUser(user, roleId);
      return new ResponseEntity<>(HttpStatus.OK);
   }

   @GetMapping("/roles")
   public ResponseEntity<List<Role>> getAllRoles() {
      List<Role> roles = userService.allRoles();
      return new ResponseEntity<>(roles, HttpStatus.OK);

   }

   @GetMapping("/userExists")
   public ResponseEntity<List<String>> userExists() {
      List<String> user = userService.userExists();
      return new ResponseEntity<>(user, HttpStatus.OK);
   }

   @GetMapping("/findUserById/{id}")
   public ResponseEntity<Users> findById(@PathVariable("id") Long id) {
      try {
         userService.findById(id);
         return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   @PutMapping("/change-password/{userId}")
public ResponseEntity<?> changePassword(
        @PathVariable("userId") Long userId,
        @RequestBody Users users) {
    
    boolean changePassword = userService.changePassword(userId, users.getOldPassword(), users.getNewPassword());
    return new ResponseEntity<>(changePassword, HttpStatus.OK);
}


@PostMapping("/changeProfilePic/{userId}")
public ResponseEntity<HttpStatus> changeProfilePic(
        @PathVariable("userId") Long userId,
        @RequestParam("profileImage") MultipartFile profileImage) {
    try {
        if (profileImage != null && !profileImage.isEmpty()) {
            // Save the uploaded file and get the saved file path
            String savedFilePath = saveProfileImage(profileImage);
            System.out.println("userId from controller: " + userId);
            System.out.println("path from controller: " + savedFilePath);

            // Call the service method to update the profile image in the database
            userService.updateProfileImage(savedFilePath, userId);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


// Method to save the uploaded file

private String saveProfileImage(MultipartFile file) throws IOException {
   // Define the absolute path for your Angular project
   String targetDirectory = "C:/projects/Angular+Springboot/AMS/Ams/ams/AmsFront/src/assets/img/faces/";
   File uploadDirectory = new File(targetDirectory);

   // Create the directory if it doesn't exist
   if (!uploadDirectory.exists()) {
       uploadDirectory.mkdirs();
   }

   // Create a unique file name to avoid collisions
   String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

   // Define the full path for the new file
   File destinationFile = new File(targetDirectory + fileName);

   // Copy the file to the destination
   Files.copy(file.getInputStream(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

   // Return the relative path for database storage
   return "/assets/img/faces/" + fileName;
}


   
}
