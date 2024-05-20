package com.finanzas.breadcredit.controller;

import com.finanzas.breadcredit.business.CustomerBusiness;
import com.finanzas.breadcredit.dto.CustomerDtoData;
import com.finanzas.breadcredit.dto.CustomerDtoInsert;
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
    public ResponseEntity<CustomerDtoData> insertCustomer(@RequestBody CustomerDtoInsert customerDtoInsert){
        try {
            Customer customer = UtilityDto.convertTo(customerDtoInsert, Customer.class);
            customer = customerBusiness.insertCustomer(customer);
            CustomerDtoData customerDtoData = UtilityDto.convertTo(customer, CustomerDtoData.class);
            return new ResponseEntity<>(customerDtoData, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error inserting the customer: " + e.getMessage(), e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDtoData> listCustomerByIdD(@PathVariable Integer id){
        try {
            Customer customer = customerBusiness.listCustomerById(id);
            CustomerDtoData customerDtoData = UtilityDto.convertTo(customer, CustomerDtoData.class);
            return new ResponseEntity<>(customerDtoData, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error listing the customer: " + e.getMessage(), e);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<CustomerDtoData>> listCustomers() {
        try {
            List<Customer> listCustomers = customerBusiness.listCustomers();
            List<CustomerDtoData> customerDtoDataList = UtilityDto.convertToList(listCustomers, CustomerDtoData.class);
            return ResponseEntity.ok(customerDtoDataList);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error listing the customers: " + e.getMessage(), e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDtoData> updateCustomer(@RequestBody CustomerDtoInsert customerDtoInsert, @PathVariable Integer id){
        try {
            Customer customer = UtilityDto.convertTo(customerDtoInsert, Customer.class);
            customer.setId(id);
            customer.getUser().setId(id);
            customer = customerBusiness.updateCustomer(customer);
            CustomerDtoData customerDtoData = UtilityDto.convertTo(customer, CustomerDtoData.class);
            return new ResponseEntity<>(customerDtoData, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating the customer: " + e.getMessage(), e);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            customerBusiness.deleteCustomer(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting the customer: " + e.getMessage());
        }
    }

}
