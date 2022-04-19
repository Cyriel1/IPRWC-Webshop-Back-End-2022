package nl.hsleiden.webshop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import nl.hsleiden.webshop.entity.Product;
import nl.hsleiden.webshop.entity.payloads.MessageResponse;
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
        Product product = productService.getProductWithImage(productId);

        if (product == null) {
            throw new Exception("Product id not found - " + productId);
        }

        return product;
    }

    @PostMapping("/product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addProduct(@RequestParam("file") MultipartFile file, @RequestParam("product") String productStringify) throws Exception {
        Product product = new ObjectMapper().readValue(productStringify, Product.class);
        product.setId(0);
        productService.saveProduct(file, product);

        return ResponseEntity.ok(new MessageResponse("Product succesfully added!"));
    }

    @PutMapping("/product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateProduct(@RequestParam("file") MultipartFile file, @RequestParam("product") String productStringify) throws Exception {
        Product product = new ObjectMapper().readValue(productStringify, Product.class);
        productService.saveProduct(file, product);

        return ResponseEntity.ok(new MessageResponse("User profile succesfully updated!"));
    }

    @DeleteMapping("/product/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable int productId) throws Exception {
        Product product = productService.getProduct(productId);

        if (product == null) {
            throw new Exception("Product id not found - " + productId);
        }

        productService.deleteProduct(productId);

        return ResponseEntity.ok(new MessageResponse("Deleted product id - " + productId));
    }

}
