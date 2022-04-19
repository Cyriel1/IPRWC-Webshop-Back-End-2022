package nl.hsleiden.webshop.dao.interfaces;

import java.util.List;

import nl.hsleiden.webshop.entity.Product;

public interface ProductDAO {
    List<Product> getProducts();
    Product getProduct(int id);
    void saveProduct(Product product);
    void deleteProduct(int id);
}
