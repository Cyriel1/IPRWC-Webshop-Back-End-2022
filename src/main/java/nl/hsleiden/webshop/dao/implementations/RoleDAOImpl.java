package nl.hsleiden.webshop.dao.implementations;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import nl.hsleiden.webshop.entity.Role;
import nl.hsleiden.webshop.entity.enums.ERole;
import nl.hsleiden.webshop.dao.interfaces.RoleDAO;

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
                currentSession.createQuery("from Role where name=:name",
                        Role.class);
        query.setParameter("name", name);
        Role role = query.uniqueResult();

        return Optional.of(role);
    }

}
