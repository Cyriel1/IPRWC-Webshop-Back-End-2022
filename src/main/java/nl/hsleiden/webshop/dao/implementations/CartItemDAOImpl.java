package nl.hsleiden.webshop.dao.implementations;

import nl.hsleiden.webshop.dao.interfaces.CartItemDAO;
import nl.hsleiden.webshop.entity.CartItem;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CartItemDAOImpl implements CartItemDAO {

    private EntityManager entityManager;

    @Autowired
    public CartItemDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<CartItem> getCartItems() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<CartItem> query =
                currentSession.createQuery("from CartItem",
                        CartItem.class);

        List<CartItem> cartItems = query.getResultList();

        return cartItems;
    }

    @Override
    public void saveCartItem(CartItem cartItem) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(cartItem);
    }

    @Override
    public CartItem getCartItem(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        CartItem cartItem = currentSession.get(CartItem.class, id);

        return cartItem;
    }

    @Override
    public void deleteCartItem(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery =
                currentSession.createQuery("delete from CartItem where id=:cartItemId");
        theQuery.setParameter("cartItemId", id);

        theQuery.executeUpdate();
    }
}
