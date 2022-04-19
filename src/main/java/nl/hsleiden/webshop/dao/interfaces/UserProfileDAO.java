package nl.hsleiden.webshop.dao.interfaces;

import nl.hsleiden.webshop.entity.UserProfile;

public interface UserProfileDAO {
    void saveUserProfile(UserProfile userProfile);
    UserProfile getUserProfile(long userId);
}
