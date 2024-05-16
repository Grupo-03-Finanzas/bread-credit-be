package com.finanzas.breadcredit.controller;

import com.finanzas.breadcredit.business.AdminBusiness;
import com.finanzas.breadcredit.dto.AdminDtoData;
import com.finanzas.breadcredit.dto.AdminDtoInsert;
import com.finanzas.breadcredit.entity.Admin;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminBusiness adminBusiness;

    @PostMapping("/insert")
    public ResponseEntity<AdminDtoData> insertAdmin(@RequestBody AdminDtoInsert adminDtoInsert){
        try {
            Admin admin = convertTo(adminDtoInsert, Admin.class);
            admin = adminBusiness.insertAdmin(admin);
            AdminDtoData adminDtoData = convertTo(admin, AdminDtoData.class);
            return new ResponseEntity<>(adminDtoData, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error inserting the admin: " + e.getMessage(), e);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<AdminDtoData>> listAdmins(){
        try {
            List<Admin> listAdmins = adminBusiness.listAdmins();
            List<AdminDtoData> adminDtoDataList = listAdmins.stream()
                    .map(admin -> convertTo(admin, AdminDtoData.class))
                    .toList();
            return ResponseEntity.ok(adminDtoDataList);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error listing the admins: " + e.getMessage(), e);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AdminDtoData> updateAdmin(@RequestBody AdminDtoInsert adminDtoInsert, @PathVariable Integer id){
        try {
            System.out.println("Fernando 1: " + adminDtoInsert);
            Admin admin = convertTo(adminDtoInsert, Admin.class);
            admin.setId(id);
            admin.getUser().setId(id);
            System.out.println("Fernando 2: " + admin);
            admin = adminBusiness.updateAdmin(admin);
            AdminDtoData adminDtoData = convertTo(admin, AdminDtoData.class);
            return new ResponseEntity<>(adminDtoData, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating the admin: " + e.getMessage(), e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            adminBusiness.deleteAdmin(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting the admin: " + e.getMessage());
        }
    }

    private <T, U> U convertTo(T source, Class<U> targetClass) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(source, targetClass);
    }
}
