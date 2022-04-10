package nl.hsleiden.webshop.controller;

import nl.hsleiden.webshop.entity.UserProfile;
import nl.hsleiden.webshop.service.interfaces.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/shop")
public class UserProfileRestController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/user-profiles")
    public List<UserProfile> getUserProfiles() {

        return userProfileService.getUserProfiles();
    }

    @GetMapping("/user-profile/{userProfileId}")
    public UserProfile getUserProfile(@PathVariable int userProfileId) throws Exception {
        UserProfile userProfile = userProfileService.getUserProfile(userProfileId);
        if (userProfile == null) throw new Exception("User profile id not found - " + userProfileId);

        return userProfile;
    }

    @PostMapping("/user-profile")
    public UserProfile addUserProfile(@RequestBody UserProfile userProfile) {
        userProfile.setId(0);
        userProfileService.saveUserProfile(userProfile);

        return userProfile;
    }

    @PutMapping("/user-profile")
    public UserProfile updateUserProfile(@RequestBody UserProfile userProfile) {
        userProfileService.saveUserProfile(userProfile);

        return userProfile;
    }

    @DeleteMapping("/user-profile/{userProfileId}")
    public String deleteUserProfile(@PathVariable int userProfileId) throws Exception {
        UserProfile userProfile = userProfileService.getUserProfile(userProfileId);
        if (userProfile == null) throw new Exception("userProfile id not found - " + userProfileId);
        userProfileService.deleteUserProfile(userProfileId);

        return "Deleted user profile id - " + userProfileId;
    }
}
