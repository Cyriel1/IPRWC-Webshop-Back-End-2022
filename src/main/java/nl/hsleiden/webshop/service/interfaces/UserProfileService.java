package nl.hsleiden.webshop.service.interfaces;

import nl.hsleiden.webshop.entity.UserProfile;

import java.util.List;

public interface UserProfileService {
    List<UserProfile> getUserProfiles();
    void saveUserProfile(UserProfile userProfile);
    UserProfile getUserProfile(int id);
    void deleteUserProfile(int id);
}
