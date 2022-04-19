package nl.hsleiden.webshop.dao.implementations;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import nl.hsleiden.webshop.entity.User;
import nl.hsleiden.webshop.dao.interfaces.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void saveUser(User user) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(user);
    }

    @Override
    public User findById(long userId) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> query =
                currentSession.createQuery("from User where id=:userId",
                        User.class);
        query.setParameter("userId", userId);
        User user = query.uniqueResult();

        return  user;
    }

    @Override
    public User findByUsername(String username) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> query =
                currentSession.createQuery("from User where username=:username",
                        User.class);
        query.setParameter("username", username);
        User user = query.uniqueResult();

        return user;
    }

    @Override
    public User findByEmail(String email) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<User> query =
                currentSession.createQuery("from User where email=:email",
                        User.class);
        query.setParameter("email", email);
        User user = query.uniqueResult();

        return user;
    }
}
