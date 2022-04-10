package nl.hsleiden.webshop.entity.payloads;

import nl.hsleiden.webshop.entity.CartItem;

import java.util.Date;
import java.util.List;

public class CartItemRequest {

    private String email;
    private List<CartItem> cartItems;

    public CartItemRequest(String email, List<CartItem> cartItems) {
        this.email = email;
        this.cartItems = cartItems;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "CartItemRequest{" +
                "email='" + email + '\'' +
                ", cartItems=" + cartItems +
                '}';
    }
}
