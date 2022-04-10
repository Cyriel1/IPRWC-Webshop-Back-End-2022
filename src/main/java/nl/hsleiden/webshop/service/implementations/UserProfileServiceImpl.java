package nl.hsleiden.webshop.service.implementations;

import nl.hsleiden.webshop.dao.interfaces.UserProfileDAO;
import nl.hsleiden.webshop.entity.UserProfile;
import nl.hsleiden.webshop.service.interfaces.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileDAO userProfileDAO;

    @Override
    @Transactional
    public List<UserProfile> getUserProfiles() {
        return userProfileDAO.getUserProfiles();
    }

    @Override
    @Transactional
    public void saveUserProfile(UserProfile userProfile) {
        userProfileDAO.saveUserProfile(userProfile);
    }

    @Override
    @Transactional
    public UserProfile getUserProfile(int id) {
        return userProfileDAO.getUserProfile(id);
    }

    @Override
    @Transactional
    public void deleteUserProfile(int id) {
        userProfileDAO.deleteUserProfile(id);
    }
}
