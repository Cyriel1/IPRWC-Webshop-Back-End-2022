package nl.hsleiden.webshop.dao.implementations;

import nl.hsleiden.webshop.dao.interfaces.RoleDAO;
import nl.hsleiden.webshop.entity.Role;
import nl.hsleiden.webshop.entity.enums.ERole;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class RoleDAOImpl implements RoleDAO {

    private EntityManager entityManager;

    @Autowired
    public RoleDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public Optional<Role> findByName(ERole name) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Role> query =
                currentSession.createQuery("from roles where name=:name",
                        Role.class);
        query.setParameter("name", name);
        Role role = query.uniqueResult();

        return Optional.of(role);
    }

}
