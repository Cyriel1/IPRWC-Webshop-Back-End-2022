package nl.hsleiden.webshop.dao.interfaces;

import nl.hsleiden.webshop.entity.User;

public interface UserDAO {
    void saveUser(User user);
    User findById(long userId);
    User findByUsername(String username);
    User findByEmail(String email);
}
