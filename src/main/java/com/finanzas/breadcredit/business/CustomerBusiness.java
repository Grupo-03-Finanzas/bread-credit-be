package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Customer;
import com.finanzas.breadcredit.repository.CustomerRepository;
import com.finanzas.breadcredit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerBusiness {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;

    public Customer insertCustomer(Customer customerIn) throws Exception {
        if (userRepository.findUserByEmail(customerIn.getUser().getEmail()) != null) {
            throw new Exception("Email already exists");
        }
        if (userRepository.findUserByDni(customerIn.getUser().getDni()) != null) {
            throw new Exception("Dni already exists");
        }
        return customerRepository.save(customerIn);
    }

    public Customer listCustomerById(Integer id) {
        return customerRepository.findCustomerById(id);
    }

    public List<Customer> listCustomers(){
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Customer customerIn) throws Exception {
        Customer customerCurrent = customerRepository.findCustomerById(customerIn.getId());
        if (userRepository.findUserByEmail(customerIn.getUser().getEmail()) != null) {
            if(!userRepository.findUserByEmail(customerIn.getUser().getEmail()).getId().equals(customerCurrent.getUser().getId())){
                throw new Exception("Email already exists");
            }
        }
        if (userRepository.findUserByDni(customerIn.getUser().getDni()) != null) {
            if(!userRepository.findUserByDni(customerIn.getUser().getDni()).getId().equals(customerCurrent.getUser().getId()) ){
                throw new Exception("Dni already exists");
            }
        }
        if (customerIn.getId() == null || customerIn.getId() == 0){
            throw new Exception("ID not present");
        }
        userRepository.save(customerIn.getUser());
        return customerRepository.save(customerIn);
    }

    public void deleteCustomer (Integer id) throws Exception {
        Customer customer = customerRepository.findCustomerById(id);
        if (customer == null) {
            throw new Exception("Customer not found");
        }
        customerRepository.delete(customer);
        userRepository.delete(customer.getUser());
    }

}
