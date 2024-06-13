package com.finanzas.breadcredit.controller;

import com.finanzas.breadcredit.business.CustomerBusiness;
import com.finanzas.breadcredit.dto.customer.CustomerDtoData;
import com.finanzas.breadcredit.dto.customer.CustomerDtoInsert;
import com.finanzas.breadcredit.dto.user.UserDtoLogin;
import com.finanzas.breadcredit.entity.Customer;
import com.finanzas.breadcredit.exception.LoginException;
import com.finanzas.breadcredit.exception.ResourceConflictException;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.exception.UnexpectedException;
import com.finanzas.breadcredit.utility.UtilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    private final CustomerBusiness customerBusiness;

    @Autowired
    public CustomerController(CustomerBusiness customerBusiness) {
        this.customerBusiness = customerBusiness;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDtoData getCustomerById(@PathVariable Integer id) throws ResourceNotFoundException, UnexpectedException {
        Customer customer = customerBusiness.getCustomerById(id);
        return UtilityDto.convertTo(customer, CustomerDtoData.class);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDtoData> getCustomers() throws ResourceNotFoundException, UnexpectedException {
        List<Customer> listCustomers = customerBusiness.listCustomers();
        return UtilityDto.convertToList(listCustomers, CustomerDtoData.class);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDtoData insertCustomer(@RequestBody CustomerDtoInsert customerDtoInsert) throws ResourceConflictException, UnexpectedException {
        Customer customer = UtilityDto.convertTo(customerDtoInsert, Customer.class);
        customer = customerBusiness.insertCustomer(customer);
        return UtilityDto.convertTo(customer, CustomerDtoData.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDtoData updateCustomer(@PathVariable Integer id, @RequestBody CustomerDtoInsert customerDtoInsert) throws ResourceNotFoundException, ResourceConflictException, UnexpectedException {
        Customer customer = UtilityDto.convertTo(customerDtoInsert, Customer.class);
        customer = customerBusiness.updateCustomer(id, customer);
        return UtilityDto.convertTo(customer, CustomerDtoData.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Void delete(@PathVariable Integer id) throws ResourceNotFoundException, UnexpectedException {
        customerBusiness.deleteCustomer(id);
        return null;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDtoData loginCustomer(@RequestBody UserDtoLogin userDtoLogin) throws LoginException, UnexpectedException {
        Customer customer = customerBusiness.loginCustomer(userDtoLogin.getDni(), userDtoLogin.getPassword());
        return UtilityDto.convertTo(customer, CustomerDtoData.class);
    }

    @GetMapping("/dni/{dni}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDtoData getCustomerByDni(@PathVariable String dni) throws ResourceNotFoundException, UnexpectedException{
        Customer customer = customerBusiness.getCustomerByDni(dni);
        return UtilityDto.convertTo(customer, CustomerDtoData.class);
    }
}
