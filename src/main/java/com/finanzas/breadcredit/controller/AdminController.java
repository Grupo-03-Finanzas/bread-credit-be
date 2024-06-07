package com.finanzas.breadcredit.controller;

import com.finanzas.breadcredit.business.AdminBusiness;
import com.finanzas.breadcredit.dto.admin.AdminDtoData;
import com.finanzas.breadcredit.dto.admin.AdminDtoInsert;
import com.finanzas.breadcredit.dto.user.UserDtoLogin;
import com.finanzas.breadcredit.entity.Admin;
import com.finanzas.breadcredit.utility.UtilityDto;
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

    @PostMapping("")
    public ResponseEntity<AdminDtoData> insertAdmin(@RequestBody AdminDtoInsert adminDtoInsert) {
        Admin admin = UtilityDto.convertTo(adminDtoInsert, Admin.class);

        try {
            admin = adminBusiness.insertAdmin(admin);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

        AdminDtoData adminDtoData = UtilityDto.convertTo(admin, AdminDtoData.class);
        return new ResponseEntity<>(adminDtoData, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDtoData> getAdminById(@PathVariable Integer id) {
        Admin admin;

        try {
            admin = adminBusiness.getAdminById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        AdminDtoData adminDtoData = UtilityDto.convertTo(admin, AdminDtoData.class);
        return new ResponseEntity<>(adminDtoData, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<AdminDtoData>> listAdmins() {
        List<Admin> listAdmins;

        try {
            listAdmins = adminBusiness.listAdmins();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        List<AdminDtoData> adminDtoDataList = UtilityDto.convertToList(listAdmins, AdminDtoData.class);
        return new ResponseEntity<>(adminDtoDataList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDtoData> updateAdmin(@PathVariable Integer id, @RequestBody AdminDtoInsert adminDtoInsert) {
        Admin admin = UtilityDto.convertTo(adminDtoInsert, Admin.class);

        try {
            admin = adminBusiness.updateAdmin(id, admin);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }

        AdminDtoData adminDtoData = UtilityDto.convertTo(admin, AdminDtoData.class);
        return new ResponseEntity<>(adminDtoData, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            adminBusiness.deleteAdmin(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AdminDtoData> loginAdmin(@RequestBody UserDtoLogin userDtoLogin) {
        Admin admin;
        try {
            admin = adminBusiness.loginAdmin(userDtoLogin.getDni(),userDtoLogin.getPassword());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
        AdminDtoData adminDtoData = UtilityDto.convertTo(admin, AdminDtoData.class);
        return new ResponseEntity<>(adminDtoData, HttpStatus.OK);
    }
}
