package nl.hsleiden.webshop.dao.interfaces;

import nl.hsleiden.webshop.entity.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getProducts();
    Product getProduct(int id);
    void saveProduct(Product product);
}
