package nl.hsleiden.webshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.hsleiden.webshop.entity.Customer;
import nl.hsleiden.webshop.service.interfaces.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {

        return customerService.getCustomers();

    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) throws Exception {

        Customer theCustomer = customerService.getCustomer(customerId);

        if (theCustomer == null) {
            throw new Exception("Customer id not found - " + customerId);
        }

        return theCustomer;
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer) {

        // also just in case the pass an id in JSON ... set id to 0
        // this is force a saveUser of new item ... instead of update

        theCustomer.setId(0);

        customerService.saveCustomer(theCustomer);

        return theCustomer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer theCustomer) {
        customerService.saveCustomer(theCustomer);

        return theCustomer;

    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) throws Exception {

        Customer tempCustomer = customerService.getCustomer(customerId);

        // throw exception if null

        if (tempCustomer == null) {
            throw new Exception("Customer id not found - " + customerId);
        }

        customerService.deleteCustomer(customerId);

        return "Deleted customer id - " + customerId;
    }

}
