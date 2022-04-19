package nl.hsleiden.webshop.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import nl.hsleiden.webshop.entity.User;
import nl.hsleiden.webshop.entity.UserProfile;
import nl.hsleiden.webshop.service.interfaces.UserService;
import nl.hsleiden.webshop.entity.payloads.MessageResponse;
import nl.hsleiden.webshop.entity.payloads.UserProfileRequest;
import nl.hsleiden.webshop.service.interfaces.UserProfileService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/shop")
public class UserProfileRestController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserService userService;

    @GetMapping("/user-profile/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public UserProfile getUserProfile(@PathVariable long userId) {
        UserProfile userProfile = userProfileService.getUserProfile(userId);

        if (userProfile == null) {
            return new UserProfile();
        }

        return userProfile;
    }

    @PostMapping("/user-profile")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addUserProfile(@Valid @RequestBody UserProfileRequest userProfileRequest) throws Exception {
        User user = userService.findById(userProfileRequest.getUserId());

        if (user == null) {
            throw new Exception("user id not found - " + userProfileRequest.getUserId());
        }

        UserProfile userProfile = userProfileRequest.getUserProfile();
        userProfile.setUser(user);
        userProfile.setId(0);
        userProfileService.saveUserProfile(userProfile);

        return ResponseEntity.ok(new MessageResponse("User profile succesfully added!"));
    }

    @PutMapping("/user-profile")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateUserProfile(@Valid @RequestBody UserProfileRequest userProfileRequest) throws Exception {
        User user = userService.findById(userProfileRequest.getUserId());

        if (user == null) {
            throw new Exception("user id not found - " + userProfileRequest.getUserId());
        }

        UserProfile userProfile = userProfileRequest.getUserProfile();
        userProfile.setUser(user);
        userProfileService.saveUserProfile(userProfile);

        return ResponseEntity.ok(new MessageResponse("User profile succesfully updated!"));
    }
}
