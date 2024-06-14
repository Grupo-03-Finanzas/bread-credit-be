package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Customer;
import com.finanzas.breadcredit.exception.LoginException;
import com.finanzas.breadcredit.exception.ResourceConflictException;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.repository.CustomerRepository;
import com.finanzas.breadcredit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerBusiness {


    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Autowired
    public CustomerBusiness(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    public Customer getCustomerById(Integer id) throws ResourceNotFoundException {
        Customer customer;
        customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer { id=" + id + " } not found");
        }
        return customer;
    }

    public List<Customer> listCustomers() throws ResourceNotFoundException {
        List<Customer> customerList = customerRepository.findAll();
        if (customerList.isEmpty()) {
            throw new ResourceNotFoundException("Customer list is empty");
        }
        return customerList;
    }

    @Transactional
    public Customer insertCustomer(Customer customer) throws ResourceConflictException {
        customer.setId(null);
        customer.getUser().setId(null);
        if (userRepository.existsByEmail(customer.getUser().getEmail())) {
            throw new ResourceConflictException("User with  { email='" + customer.getUser().getEmail() + "' } already exists");
        }
        if (userRepository.existsByDni(customer.getUser().getDni())) {
            throw new ResourceConflictException("User with  { dni='" + customer.getUser().getDni() + "' } already exists");
        }
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer updateCustomer(Integer id, Customer customer) throws ResourceNotFoundException, ResourceConflictException {
        customer.setId(id);
        customer.getUser().setId(id);
        Customer customerOld = getCustomerById(id);
        if (!customerOld.getUser().getEmail().equals(customer.getUser().getEmail())) {
            if (userRepository.existsByEmail(customer.getUser().getEmail())) {
                throw new ResourceConflictException("User with  { email='" + customer.getUser().getEmail() + "' } already exists");
            }
        }
        if (!customerOld.getUser().getDni().equals(customer.getUser().getDni())) {
            if (userRepository.existsByDni(customer.getUser().getDni())) {
                throw new ResourceConflictException("User with  { dni='" + customer.getUser().getDni() + "' } already exists");
            }
        }
        userRepository.save(customer.getUser()); //lookup for CascadeType
        return customerRepository.save(customer);
    }

    @Transactional
    public void deleteCustomer(Integer id) throws ResourceNotFoundException {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
        userRepository.delete(customer.getUser());
    }

    public Customer loginCustomer(String dni, String password) throws LoginException {
        Customer customer;
        customer = customerRepository.findByUser_Dni(dni).orElse(null);
        if (customer == null) {
            throw new LoginException("Invalid email or password");
        }
        if (!customer.getUser().getPassword().equals(password)) {
            throw new LoginException("Invalid email or password");
        }
        return customer;
    }

    public Customer getCustomerByDni(String dni) throws ResourceNotFoundException {
        Customer customer;
        customer = customerRepository.findByUser_Dni(dni).orElse(null);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer { dni=" + dni + " } not found");
        }
        return customer;
    }
}
