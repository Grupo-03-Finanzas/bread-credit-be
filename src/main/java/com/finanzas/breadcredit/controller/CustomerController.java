package com.finanzas.breadcredit.controller;

import com.finanzas.breadcredit.business.CustomerBusiness;
import com.finanzas.breadcredit.dto.customer.CustomerDtoData;
import com.finanzas.breadcredit.dto.customer.CustomerDtoInsert;
import com.finanzas.breadcredit.dto.customer.CustomerDtoLogin;
import com.finanzas.breadcredit.entity.Customer;
import com.finanzas.breadcredit.utility.UtilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerBusiness customerBusiness;

    @PostMapping("")
    public ResponseEntity<CustomerDtoData> insertCustomer(@RequestBody CustomerDtoInsert customerDtoInsert) {
        Customer customer = UtilityDto.convertTo(customerDtoInsert, Customer.class);

        try {
            customer = customerBusiness.insertCustomer(customer);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage(), e);
        }

        CustomerDtoData customerDtoData = UtilityDto.convertTo(customer, CustomerDtoData.class);
        return new ResponseEntity<>(customerDtoData, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDtoData> getCustomerById(@PathVariable Integer id) {
        Customer customer;

        try {
            customer = customerBusiness.getCustomerById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

        CustomerDtoData customerDtoData = UtilityDto.convertTo(customer, CustomerDtoData.class);
        return new ResponseEntity<>(customerDtoData, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<CustomerDtoData>> getCustomers() {
        List<Customer> listCustomers;

        try {
            listCustomers = customerBusiness.listCustomers();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        List<CustomerDtoData> customerDtoDataList = UtilityDto.convertToList(listCustomers, CustomerDtoData.class);
        return new ResponseEntity<>(customerDtoDataList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDtoData> updateCustomer(@PathVariable Integer id ,@RequestBody CustomerDtoInsert customerDtoInsert) {
        Customer customer = UtilityDto.convertTo(customerDtoInsert, Customer.class);

        try {
            customer = customerBusiness.updateCustomer(id, customer);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        CustomerDtoData customerDtoData = UtilityDto.convertTo(customer, CustomerDtoData.class);
        return new ResponseEntity<>(customerDtoData, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            customerBusiness.deleteCustomer(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<CustomerDtoLogin> loginCustomer(@RequestBody CustomerDtoInsert customerDtoInsert) {
        Customer customer = UtilityDto.convertTo(customerDtoInsert, Customer.class);
        try {
            customer = customerBusiness.loginCustomer(customer);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        CustomerDtoLogin customerDtoLogin = UtilityDto.convertTo(customer,CustomerDtoLogin.class);
        return new ResponseEntity<>(customerDtoLogin, HttpStatus.OK);
    }
}
