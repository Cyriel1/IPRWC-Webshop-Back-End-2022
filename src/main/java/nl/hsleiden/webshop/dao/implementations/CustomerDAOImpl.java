package nl.hsleiden.webshop.dao.implementations;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nl.hsleiden.webshop.dao.interfaces.CustomerDAO;
import nl.hsleiden.webshop.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private EntityManager entityManager;

    @Autowired
    public CustomerDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Customer> getCustomers() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Customer> theQuery =
                currentSession.createQuery("from Customer order by lastName",
                        Customer.class);

        List<Customer> customers = theQuery.getResultList();

        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Customer customer = currentSession.get(Customer.class, id);

        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery =
                currentSession.createQuery("delete from Customer where id=:customerId");
        theQuery.setParameter("customerId", id);

        theQuery.executeUpdate();
    }

}
