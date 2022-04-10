package nl.hsleiden.webshop.service.implementations;

import nl.hsleiden.webshop.dao.interfaces.RoleDAO;
import nl.hsleiden.webshop.entity.enums.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nl.hsleiden.webshop.entity.Role;
import nl.hsleiden.webshop.service.interfaces.RoleService;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    @Transactional
    public Set<Role> verifyRoles(Set<String> requestRoles){
        Set<Role> roles = new HashSet<>();

        if (requestRoles == null) {
            Role userRole = roleDAO.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);

            return roles;
        }

        requestRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleDAO.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                    break;
                default:
                    Role userRole = roleDAO.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
            }
        });

        return roles;
    }
}
