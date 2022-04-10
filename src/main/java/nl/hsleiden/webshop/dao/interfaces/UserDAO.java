package nl.hsleiden.webshop.dao.interfaces;

import nl.hsleiden.webshop.entity.User;

import java.util.Optional;

public interface UserDAO {
    void saveUser(User user);
    User findByUsername(String username);
    User findByEmail(String email);
}
