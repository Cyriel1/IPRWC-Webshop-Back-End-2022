package nl.hsleiden.webshop.dao.implementations;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import nl.hsleiden.webshop.entity.Product;
import nl.hsleiden.webshop.dao.interfaces.ProductDAO;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private EntityManager entityManager;

    @Autowired
    public ProductDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Product> getProducts() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Product> theQuery =
                currentSession.createQuery("from Product",
                        Product.class);

        List<Product> products = theQuery.getResultList();

        return products;
    }

    @Override
    public Product getProduct(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Product product = currentSession.get(Product.class, id);

        return product;
    }

    @Override
    public void saveProduct(Product product) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(product);
    }

    @Override
    public void deleteProduct(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery =
                currentSession.createQuery("delete from Product where id=:productId");
        theQuery.setParameter("productId", id);

        theQuery.executeUpdate();
    }

}
