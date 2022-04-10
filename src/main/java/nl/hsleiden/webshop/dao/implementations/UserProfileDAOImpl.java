package nl.hsleiden.webshop.dao.implementations;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nl.hsleiden.webshop.dao.interfaces.UserProfileDAO;
import nl.hsleiden.webshop.entity.UserProfile;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserProfileDAOImpl implements UserProfileDAO {

    private EntityManager entityManager;

    @Autowired
    public UserProfileDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<UserProfile> getUserProfiles() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<UserProfile> query =
                currentSession.createQuery("from UserProfile order by lastName",
                        UserProfile.class);

        List<UserProfile> userProfiles = query.getResultList();

        return userProfiles;
    }

    @Override
    public void saveUserProfile(UserProfile userProfile) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(userProfile);
    }

    @Override
    public UserProfile getUserProfile(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        UserProfile userProfile = currentSession.get(UserProfile.class, id);

        return userProfile;
    }

    @Override
    public void deleteUserProfile(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery =
                currentSession.createQuery("delete from UserProfile where id=:userProfileId");
        theQuery.setParameter("userProfileId", id);

        theQuery.executeUpdate();
    }
}
