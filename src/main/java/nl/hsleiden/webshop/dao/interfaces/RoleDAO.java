package nl.hsleiden.webshop.dao.interfaces;

import java.util.Optional;

import nl.hsleiden.webshop.entity.Role;
import nl.hsleiden.webshop.entity.enums.ERole;

public interface RoleDAO {
    Optional<Role> findByName(ERole name);
}
