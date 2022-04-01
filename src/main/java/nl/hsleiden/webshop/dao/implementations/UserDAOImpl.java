package nl.hsleiden.webshop.dao.implementations;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.Optional;

import nl.hsleiden.webshop.dao.interfaces.UserDAO;
import nl.hsleiden.webshop.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> query =
                currentSession.createQuery("from users where username=:username",
                        User.class);
        query.setParameter("username", username);
        User user = query.uniqueResult();

        return Optional.of(user);
    }

    @Override
    public Boolean existsByUsername(String username) {
        Optional<User> user = findByUsername(username);

        return user.isPresent();
    }

    @Override
    public Boolean existsByEmail(String email) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> query =
                currentSession.createQuery("from users where email=:email",
                        User.class);
        query.setParameter("email", email);
        User user = query.uniqueResult();

        return user != null;
    }

}
