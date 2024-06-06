package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Customer;
import com.finanzas.breadcredit.repository.CustomerRepository;
import com.finanzas.breadcredit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerBusiness {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    public Customer insertCustomer(Customer customer) throws Exception {
        if(userRepository.existsByEmail(customer.getUser().getEmail())) {
            throw new Exception("Email Already Exists");
        }
        if(userRepository.existsByDni(customer.getUser().getDni())) {
            throw new Exception("Dni Already Exists");
        }

        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Integer id) throws Exception{
        return customerRepository.findById(id).orElseThrow(() -> new Exception("Customer not found"));
    }

    public List<Customer> listCustomers() throws Exception {
        List<Customer> customerList = customerRepository.findAll();

        //if (customerList.isEmpty()) {
        //    throw new Exception("Customers not found");
        //}

        return customerList;
    }

    @Transactional
    public Customer updateCustomer(Integer id, Customer customer) throws Exception {
        customer.setId(id);
        customer.getUser().setId(id);

        if (!customerRepository.existsById(customer.getId())) {
            throw new Exception("Customer not found");
        }
        Customer customerExists = customerRepository.findById(customer.getId()).orElse(new Customer());
        if (!customerExists.getUser().getEmail().equals(customer.getUser().getEmail())) {
            if(userRepository.existsByEmail(customer.getUser().getEmail())) {
                throw new Exception("Email Already Exists");
            }
        }
        if (!customerExists.getUser().getDni().equals(customer.getUser().getDni())) {
            if(userRepository.existsByDni(customer.getUser().getDni())) {
                throw new Exception("Dni Already Exists");
            }
        }

        userRepository.save(customer.getUser());
        return customerRepository.save(customer);
    }

    @Transactional
    public void deleteCustomer(Integer id) throws Exception {
        Customer customerCurrent = customerRepository.findById(id).orElseThrow(() -> new Exception("Customer not found"));
        customerRepository.delete(customerCurrent);
        userRepository.delete(customerCurrent.getUser());
    }

    public Customer loginCustomer(Customer customer) throws Exception {
        Customer customerExists = customerRepository.findByUserDni(customer.getUser().getDni()).orElseThrow(() -> new Exception("Customer not found"));
        if (!customerExists.getUser().getPassword().equals(customer.getUser().getPassword())) {
            throw new Exception("Wrong password");
        }
        return customerExists;
    }
}
