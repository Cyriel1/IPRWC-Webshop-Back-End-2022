package nl.hsleiden.webshop.dao.interfaces;

import java.util.List;

import nl.hsleiden.webshop.entity.Order;
import nl.hsleiden.webshop.entity.payloads.OrdersRequest;

public interface OrderDAO {
    List<Order> getOrderItems(long userId);
    void saveOrderItem(Order order);
    void deleteOrderItems(OrdersRequest ordersRequest);
}
