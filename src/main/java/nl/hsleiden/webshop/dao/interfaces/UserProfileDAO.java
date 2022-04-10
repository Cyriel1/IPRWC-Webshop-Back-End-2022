package nl.hsleiden.webshop.dao.interfaces;

import nl.hsleiden.webshop.entity.UserProfile;

import java.util.List;

public interface UserProfileDAO {
    List<UserProfile> getUserProfiles();
    void saveUserProfile(UserProfile userProfile);
    UserProfile getUserProfile(int id);
    void deleteUserProfile(int id);
}
