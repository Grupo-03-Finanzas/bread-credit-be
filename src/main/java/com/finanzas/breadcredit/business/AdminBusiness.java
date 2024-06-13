package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Admin;
import com.finanzas.breadcredit.exception.LoginException;
import com.finanzas.breadcredit.exception.ResourceConflictException;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.exception.UnexpectedException;
import com.finanzas.breadcredit.repository.AdminRepository;
import com.finanzas.breadcredit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminBusiness {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdminBusiness(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    public Admin getAdminById(Integer id) throws ResourceNotFoundException, UnexpectedException {
        Admin admin;
        try {
            admin = adminRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new UnexpectedException(e.getMessage());
        }
        if (admin == null) {
            throw new ResourceNotFoundException("Admin { id=" + id + " } not found");
        }
        return admin;
    }

    public List<Admin> listAdmins() throws ResourceNotFoundException, UnexpectedException {
        List<Admin> adminList;
        try {
            adminList = adminRepository.findAll();
        } catch (Exception e) {
            throw new UnexpectedException(e.getMessage());
        }
        if (adminList.isEmpty()) {
            throw new ResourceNotFoundException("Admin list is empty");
        }
        return adminList;
    }

    @Transactional
    public Admin insertAdmin(Admin admin) throws ResourceConflictException, UnexpectedException {
        admin.setId(null);
        admin.getUser().setId(null);
        if (userRepository.existsByEmail(admin.getUser().getEmail())) {
            throw new ResourceConflictException("User with  { email='" + admin.getUser().getEmail() + "' } already exists");
        }
        if (userRepository.existsByDni(admin.getUser().getDni())) {
            throw new ResourceConflictException("User with  { dni='" + admin.getUser().getDni() + "' } already exists");
        }
        try {
            return adminRepository.save(admin);
        } catch (Exception e) {
            throw new UnexpectedException(e.getMessage());
        }
    }

    @Transactional
    public Admin updateAdmin(Integer id, Admin admin) throws ResourceNotFoundException, ResourceConflictException, UnexpectedException {
        admin.setId(id);
        admin.getUser().setId(id);
        Admin adminOld = getAdminById(id);
        if (!adminOld.getUser().getEmail().equals(admin.getUser().getEmail())) {
            if (userRepository.existsByEmail(admin.getUser().getEmail())) {
                throw new ResourceConflictException("User with  { email='" + admin.getUser().getEmail() + "' } already exists");
            }
        }
        if (!adminOld.getUser().getDni().equals(admin.getUser().getDni())) {
            if (userRepository.existsByDni(admin.getUser().getDni())) {
                throw new ResourceConflictException("User with  { dni='" + admin.getUser().getDni() + "' } already exists");
            }
        }
        try {
            userRepository.save(admin.getUser()); //lookup for CascadeType
            return adminRepository.save(admin);
        } catch (Exception e) {
            throw new UnexpectedException(e.getMessage());
        }
    }

    @Transactional
    public void deleteAdmin(Integer id) throws ResourceNotFoundException, UnexpectedException {
        Admin admin = getAdminById(id);
        try {
            adminRepository.delete(admin);
            userRepository.delete(admin.getUser());
        } catch (Exception e) {
            throw new UnexpectedException(e.getMessage());
        }
    }

    public Admin loginAdmin(String dni, String password) throws LoginException, UnexpectedException {
        Admin admin;
        try {
            admin = adminRepository.findByUserDni(dni).orElse(null);
        } catch (Exception e) {
            throw new UnexpectedException(e.getMessage());
        }
        if (admin == null) {
            throw new LoginException("Invalid email or password");
        }
        if (!admin.getUser().getPassword().equals(password)) {
            throw new LoginException("Invalid email or password");
        }
        return admin;
    }
}
