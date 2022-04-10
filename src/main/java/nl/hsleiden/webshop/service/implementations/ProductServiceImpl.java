package nl.hsleiden.webshop.service.implementations;

import nl.hsleiden.webshop.dao.interfaces.ProductDAO;
import nl.hsleiden.webshop.entity.Product;
import nl.hsleiden.webshop.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    @Transactional
    public List<Product> getProducts() {
        return productDAO.getProducts();
    }

    @Override
    public Product getProduct(int id) {
        return productDAO.getProduct(id);
    }

    @Override
    @Transactional
    public void saveProduct(Product product) {
        productDAO.saveProduct(product);
    }

}
