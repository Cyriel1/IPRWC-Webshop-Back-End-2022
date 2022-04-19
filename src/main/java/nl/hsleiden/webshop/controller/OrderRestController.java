package nl.hsleiden.webshop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import nl.hsleiden.webshop.entity.User;
import nl.hsleiden.webshop.entity.Order;
import nl.hsleiden.webshop.entity.payloads.OrdersRequest;
import nl.hsleiden.webshop.service.interfaces.UserService;
import nl.hsleiden.webshop.entity.payloads.MessageResponse;
import nl.hsleiden.webshop.service.interfaces.OrderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/shop")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/orders/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Order> getOrders(@PathVariable long userId) throws Exception {
        List<Order> orders = orderService.getOrderItems(userId);

        if (orders == null) {
            throw new Exception("Requested Orders of user is not found");
        }

        return orders;
    }

    @PostMapping("/orders")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addOrders(@Valid @RequestBody OrdersRequest ordersRequest) {
        User user = userService.findById(ordersRequest.getUserId());
        orderService.saveOrderItems(user, ordersRequest.getOrders());

        return ResponseEntity.ok(new MessageResponse("Orders has been placed!"));
    }

    @DeleteMapping("/orders")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteOrders(@Valid @RequestBody OrdersRequest ordersRequest) throws Exception {
        List<Order> orders = orderService.getOrderItems(ordersRequest.getUserId());

        if (orders == null) {
            throw new Exception("Requested Orders not found");
        }

        orderService.deleteOrderItems(ordersRequest);

        return ResponseEntity.ok(new MessageResponse("Order has been deleted - timestamp order: " + ordersRequest.getTimestamp()));
    }
}
