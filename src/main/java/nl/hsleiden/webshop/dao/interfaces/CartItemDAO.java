package nl.hsleiden.webshop.dao.interfaces;

import nl.hsleiden.webshop.entity.CartItem;

import java.util.List;

public interface CartItemDAO {
    List<CartItem> getCartItems();
    void saveCartItem(CartItem cartItem);
    CartItem getCartItem(int id);
    void deleteCartItem(int id);
}
