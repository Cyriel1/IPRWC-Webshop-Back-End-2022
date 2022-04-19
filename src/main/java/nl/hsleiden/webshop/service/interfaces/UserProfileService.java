package nl.hsleiden.webshop.service.interfaces;

import nl.hsleiden.webshop.entity.UserProfile;

public interface UserProfileService {
    void saveUserProfile(UserProfile userProfile);
    UserProfile getUserProfile(long userId);
}
