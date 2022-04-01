package nl.hsleiden.webshop.dao.interfaces;

import nl.hsleiden.webshop.entity.Role;
import nl.hsleiden.webshop.entity.enums.ERole;

import java.util.Optional;

public interface RoleDAO {
    Optional<Role> findByName(ERole name);
}
