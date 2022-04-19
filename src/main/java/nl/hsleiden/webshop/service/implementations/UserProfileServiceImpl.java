package nl.hsleiden.webshop.service.implementations;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nl.hsleiden.webshop.entity.UserProfile;
import nl.hsleiden.webshop.dao.interfaces.UserProfileDAO;
import nl.hsleiden.webshop.service.interfaces.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileDAO userProfileDAO;

    @Override
    @Transactional
    public void saveUserProfile(UserProfile userProfile) {
        userProfileDAO.saveUserProfile(userProfile);
    }

    @Override
    @Transactional
    public UserProfile getUserProfile(long userId) {
        return userProfileDAO.getUserProfile(userId);
    }
}
