package nl.hsleiden.webshop.service.implementations;

import java.util.Set;
import java.util.HashSet;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nl.hsleiden.webshop.entity.Role;
import nl.hsleiden.webshop.entity.enums.ERole;
import nl.hsleiden.webshop.dao.interfaces.RoleDAO;
import nl.hsleiden.webshop.service.interfaces.RoleService;

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
                //... cases can be created on the spot for new roles.
                default:
                    Role userRole = roleDAO.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
            }
        });

        return roles;
    }
}
