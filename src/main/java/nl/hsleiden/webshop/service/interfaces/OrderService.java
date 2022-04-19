package nl.hsleiden.webshop.service.interfaces;

import java.util.List;

import nl.hsleiden.webshop.entity.Order;
import nl.hsleiden.webshop.entity.User;
import nl.hsleiden.webshop.entity.payloads.OrdersRequest;

public interface OrderService {
    List<Order> getOrderItems(long userId);
    void saveOrderItems(User user, List<Order> orders);
    void deleteOrderItems(OrdersRequest ordersRequest);
}
