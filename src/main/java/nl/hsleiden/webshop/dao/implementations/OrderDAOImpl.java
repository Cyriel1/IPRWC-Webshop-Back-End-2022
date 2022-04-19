package nl.hsleiden.webshop.dao.implementations;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import nl.hsleiden.webshop.entity.Order;
import nl.hsleiden.webshop.dao.interfaces.OrderDAO;
import nl.hsleiden.webshop.entity.payloads.OrdersRequest;

@Repository
public class OrderDAOImpl implements OrderDAO {

    private EntityManager entityManager;

    @Autowired
    public OrderDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Order> getOrderItems(long userId) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Order> query =
                currentSession.createQuery("from Order where user.id=:userId",
                        Order.class);
        query.setParameter("userId", userId);

        List<Order> orders = query.getResultList();

        return orders;
    }

    @Override
    public void saveOrderItem(Order order) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(order);
    }

    @Override
    public void deleteOrderItems(OrdersRequest ordersRequest) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query query =
                currentSession.createQuery("delete from Order where user.id=:userId and timestamp=:timestamp");
        query.setParameter("userId", ordersRequest.getUserId());
        query.setParameter("timestamp", ordersRequest.getTimestamp());

        query.executeUpdate();
    }
}
