package nl.hsleiden.webshop.service.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import nl.hsleiden.webshop.entity.Product;

public interface ProductService {
    List<Product> getProducts();
    Product getProductWithImage(int id);
    Product getProduct(int id);
    void saveProduct(MultipartFile file, Product product) throws Exception;
    void deleteProduct(int id);
}
