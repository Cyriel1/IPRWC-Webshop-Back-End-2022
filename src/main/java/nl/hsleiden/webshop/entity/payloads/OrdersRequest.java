package nl.hsleiden.webshop.entity.payloads;

import java.util.List;

import javax.validation.constraints.NotNull;

import nl.hsleiden.webshop.entity.Order;

public class OrdersRequest {

    @NotNull
    private long userId;

    private List<Order> orders;

    private String timestamp;

    public OrdersRequest(long userId, List<Order> orders) {
        this.userId = userId;
        this.orders = orders;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrdersRequest{" +
                "userId=" + userId +
                ", orders=" + orders +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
