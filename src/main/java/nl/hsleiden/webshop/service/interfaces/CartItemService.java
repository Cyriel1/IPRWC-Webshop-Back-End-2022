package nl.hsleiden.webshop.service.interfaces;

import nl.hsleiden.webshop.entity.CartItem;

import java.util.List;

public interface CartItemService {
    List<CartItem> getCartItems();
    void saveCartItem(CartItem cartItem);
    CartItem getCartItem(int id);
    void deleteCartItem(int id);
}
