package nl.hsleiden.webshop.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.hsleiden.webshop.dao.interfaces.UserDAO;
import nl.hsleiden.webshop.entity.User;
import nl.hsleiden.webshop.service.interfaces.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    @Transactional
    public Boolean existsByUsername(String username) {
        User user = userDAO.findByUsername(username);

        return user != null;
    }

    @Override
    @Transactional
    public Boolean existsByEmail(String email) {
        User user = userDAO.findByEmail(email);

        return user != null;
    }
}
