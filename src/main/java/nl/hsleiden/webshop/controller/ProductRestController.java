package nl.hsleiden.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import nl.hsleiden.webshop.entity.Product;
import nl.hsleiden.webshop.service.interfaces.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/shop")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts() {

        return productService.getProducts();
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable int productId) throws Exception {

        Product product = productService.getProduct(productId);

        if (product == null) {
            throw new Exception("Product id not found - " + productId);
        }

        return product;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        product.setId(0);
        productService.saveProduct(product);

        return product;
    }

}
