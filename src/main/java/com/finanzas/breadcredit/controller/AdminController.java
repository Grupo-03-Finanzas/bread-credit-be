package com.finanzas.breadcredit.controller;

import com.finanzas.breadcredit.business.AdminBusiness;
import com.finanzas.breadcredit.dto.admin.AdminDtoData;
import com.finanzas.breadcredit.dto.admin.AdminDtoInsert;
import com.finanzas.breadcredit.dto.user.UserDtoLogin;
import com.finanzas.breadcredit.entity.Admin;
import com.finanzas.breadcredit.exception.LoginException;
import com.finanzas.breadcredit.exception.ResourceConflictException;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.utility.UtilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminBusiness adminBusiness;

    @Autowired
    public AdminController(AdminBusiness adminBusiness) {
        this.adminBusiness = adminBusiness;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdminDtoData getAdminById(@PathVariable Integer id) throws ResourceNotFoundException {
        Admin admin = adminBusiness.getAdminById(id);
        return UtilityDto.convertTo(admin, AdminDtoData.class);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<AdminDtoData> listAdmins() throws ResourceNotFoundException {
        List<Admin> listAdmins = adminBusiness.listAdmins();
        return UtilityDto.convertToList(listAdmins, AdminDtoData.class);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public AdminDtoData insertAdmin(@RequestBody AdminDtoInsert adminDtoInsert) throws ResourceConflictException {
        Admin admin = UtilityDto.convertTo(adminDtoInsert, Admin.class);
        admin = adminBusiness.insertAdmin(admin);
        return UtilityDto.convertTo(admin, AdminDtoData.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdminDtoData updateAdmin(@PathVariable Integer id, @RequestBody AdminDtoInsert adminDtoInsert) throws ResourceNotFoundException, ResourceConflictException {
        Admin admin = UtilityDto.convertTo(adminDtoInsert, Admin.class);
        admin = adminBusiness.updateAdmin(id, admin);
        return UtilityDto.convertTo(admin, AdminDtoData.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdmin(@PathVariable Integer id) throws ResourceNotFoundException {
        adminBusiness.deleteAdmin(id);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AdminDtoData loginAdmin(@RequestBody UserDtoLogin userDtoLogin) throws LoginException {
        Admin  admin = adminBusiness.loginAdmin(userDtoLogin.getDni(), userDtoLogin.getPassword());
        return UtilityDto.convertTo(admin, AdminDtoData.class);
    }
}
