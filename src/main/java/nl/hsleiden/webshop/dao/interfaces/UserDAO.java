package nl.hsleiden.webshop.dao.interfaces;

import nl.hsleiden.webshop.entity.User;

import java.util.Optional;

public interface UserDAO {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
