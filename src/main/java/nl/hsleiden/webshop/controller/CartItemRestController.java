package nl.hsleiden.webshop.controller;

import nl.hsleiden.webshop.entity.CartItem;
import nl.hsleiden.webshop.entity.User;
import nl.hsleiden.webshop.entity.payloads.CartItemRequest;
import nl.hsleiden.webshop.entity.payloads.MessageResponse;
import nl.hsleiden.webshop.service.interfaces.CartItemService;

import nl.hsleiden.webshop.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/shop")
public class CartItemRestController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @GetMapping("/cart-item")
    public List<CartItem> getCartItems() {

        return cartItemService.getCartItems();
    }

    @GetMapping("/cart-item/{cartItemId}")
    public CartItem getCartItem(@PathVariable int cartItemId) throws Exception {
        CartItem cartItem = cartItemService.getCartItem(cartItemId);
        if (cartItem == null) throw new Exception("Cart Item id not found - " + cartItemId);

        return cartItem;
    }

    @PostMapping("/cart-item")
    public ResponseEntity<?> addCartItem(@RequestBody CartItemRequest cartItemRequest) {
        User user = userService.findByEmail(cartItemRequest.getEmail());
        ZonedDateTime timestamp = ZonedDateTime.now();

        for (CartItem cartItem : cartItemRequest.getCartItems()) {
            cartItem.setUser(user);
            cartItem.setTimestamp(timestamp);
            cartItemService.saveCartItem(cartItem);
        }

        return ResponseEntity.ok(new MessageResponse("Succesfully stored cart"));
    }

    @PutMapping("/cart-item")
    public CartItem updateCartItem(@RequestBody CartItem cartItem) {
        cartItemService.saveCartItem(cartItem);

        return cartItem;
    }

    @DeleteMapping("/cart-item/{cartItemId}")
    public String deleteCartItem(@PathVariable int cartItemId) throws Exception {
        CartItem cartItem = cartItemService.getCartItem(cartItemId);
        if (cartItem == null) throw new Exception("cartItem id not found - " + cartItemId);
        cartItemService.deleteCartItem(cartItemId);

        return "Deleted cartItem id - " + cartItemId;
    }
}
