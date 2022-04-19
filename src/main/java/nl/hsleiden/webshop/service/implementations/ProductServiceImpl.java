package nl.hsleiden.webshop.service.implementations;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import java.util.Base64;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import nl.hsleiden.webshop.entity.Product;
import nl.hsleiden.webshop.dao.interfaces.ProductDAO;
import nl.hsleiden.webshop.config.FileStorageProperties;
import nl.hsleiden.webshop.service.interfaces.ProductService;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private FileStorageProperties fileStorageProperties;

    @Autowired
    private ProductDAO productDAO;

    @Override
    @Transactional
    public List<Product> getProducts() {
        List<Product> products = productDAO.getProducts();

        for (Product product : products) {
            if (product.getImagePath() != null){
                String image = convertImageToBase64(product.getImagePath());
                product.setImage(image);
            }
        }

        return products;
    }

    @Override
    @Transactional
    public Product getProductWithImage(int id) {
        Product product = productDAO.getProduct(id);
        if (product.getImagePath() != null){
            String image = convertImageToBase64(product.getImagePath());
            product.setImage(image);
        }

        return product;
    }

    @Override
    @Transactional
    public Product getProduct(int id) {
        Product product = productDAO.getProduct(id);

        return product;
    }

    @Override
    @Transactional
    public void saveProduct(MultipartFile file, Product product) throws Exception {
        if (!file.getOriginalFilename().equals("blob")) {
            product.setImagePath(storeFile(file));
        }
        productDAO.saveProduct(product);
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }

    private String convertImageToBase64(String filePath){
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));

            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException convertingException) {

            return "";
        }
    }

    private String storeFile(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path fileStorageLocation = Paths.get(fileStorageProperties.getUploadDirectory()).toAbsolutePath().normalize();

        try {
            if(fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileStorageProperties.getUploadDirectory() + '/' + fileName;
        } catch (IOException ex) {
            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

}
