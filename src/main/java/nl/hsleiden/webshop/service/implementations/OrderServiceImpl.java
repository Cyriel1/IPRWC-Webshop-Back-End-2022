package nl.hsleiden.webshop.service.implementations;

import java.util.List;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nl.hsleiden.webshop.entity.User;
import nl.hsleiden.webshop.entity.Order;
import nl.hsleiden.webshop.dao.interfaces.OrderDAO;
import nl.hsleiden.webshop.entity.payloads.OrdersRequest;
import nl.hsleiden.webshop.service.interfaces.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    @Transactional
    public List<Order> getOrderItems(long userId) {
        return orderDAO.getOrderItems(userId);
    }

    @Override
    @Transactional
    public void saveOrderItems(User user, List<Order> orders) {
        ZonedDateTime timestamp = ZonedDateTime.now();

        for (Order order : orders) {
            order.setUser(user);
            order.setTimestamp(timestamp);
            orderDAO.saveOrderItem(order);
        }
    }

    @Override
    @Transactional
    public void deleteOrderItems(OrdersRequest ordersRequest) {
        orderDAO.deleteOrderItems(ordersRequest);
    }
}
