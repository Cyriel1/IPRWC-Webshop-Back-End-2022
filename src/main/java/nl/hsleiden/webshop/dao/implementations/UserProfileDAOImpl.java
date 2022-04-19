package nl.hsleiden.webshop.dao.implementations;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import nl.hsleiden.webshop.entity.UserProfile;
import nl.hsleiden.webshop.dao.interfaces.UserProfileDAO;

@Repository
public class UserProfileDAOImpl implements UserProfileDAO {

    private EntityManager entityManager;

    @Autowired
    public UserProfileDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void saveUserProfile(UserProfile userProfile) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(userProfile);
    }

    @Override
    public UserProfile getUserProfile(long userId) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<UserProfile> query =
                currentSession.createQuery("from UserProfile where user.id=:userId",
                        UserProfile.class);
        query.setParameter("userId", userId);

        UserProfile userProfile = query.uniqueResult();

        return userProfile;
    }
}
