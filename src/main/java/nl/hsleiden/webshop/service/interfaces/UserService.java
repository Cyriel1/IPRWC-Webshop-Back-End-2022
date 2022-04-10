package nl.hsleiden.webshop.service.interfaces;

import nl.hsleiden.webshop.entity.User;

public interface UserService {
    void saveUser(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
