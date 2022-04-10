package nl.hsleiden.webshop.service.implementations;

import nl.hsleiden.webshop.dao.interfaces.CartItemDAO;
import nl.hsleiden.webshop.entity.CartItem;
import nl.hsleiden.webshop.service.interfaces.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemDAO cartItemDAO;

    @Override
    @Transactional
    public List<CartItem> getCartItems() {
        return cartItemDAO.getCartItems();
    }

    @Override
    @Transactional
    public void saveCartItem(CartItem cartItem) {
        cartItemDAO.saveCartItem(cartItem);
    }

    @Override
    @Transactional
    public CartItem getCartItem(int id) {
        return cartItemDAO.getCartItem(id);
    }

    @Override
    @Transactional
    public void deleteCartItem(int id) {
        cartItemDAO.deleteCartItem(id);
    }
}
