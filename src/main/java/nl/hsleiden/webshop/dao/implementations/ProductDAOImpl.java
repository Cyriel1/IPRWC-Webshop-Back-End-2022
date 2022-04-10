package nl.hsleiden.webshop.dao.implementations;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import nl.hsleiden.webshop.dao.interfaces.ProductDAO;
import nl.hsleiden.webshop.entity.Product;


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

}
