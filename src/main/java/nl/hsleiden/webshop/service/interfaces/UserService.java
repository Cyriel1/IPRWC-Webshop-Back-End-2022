package nl.hsleiden.webshop.service.interfaces;

import nl.hsleiden.webshop.entity.User;

public interface UserService {
    void saveUser(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(long userId);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
