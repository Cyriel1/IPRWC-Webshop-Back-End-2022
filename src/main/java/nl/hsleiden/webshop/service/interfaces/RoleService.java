package nl.hsleiden.webshop.service.interfaces;

import java.util.Set;

import nl.hsleiden.webshop.entity.Role;

public interface RoleService {
    Set<Role> verifyRoles(Set<String> requestRoles);
}
