package nl.hsleiden.webshop.service.interfaces;

import nl.hsleiden.webshop.entity.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> verifyRoles(Set<String> requestRoles);
}
