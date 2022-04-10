package nl.hsleiden.webshop.service.interfaces;

import nl.hsleiden.webshop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProduct(int id);
    void saveProduct(Product product);
}
