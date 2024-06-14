package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Admin;
import com.finanzas.breadcredit.exception.LoginException;
import com.finanzas.breadcredit.exception.ResourceConflictException;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
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

    public Admin getAdminById(Integer id) throws ResourceNotFoundException {
        Admin admin;
        admin = adminRepository.findById(id).orElse(null);
        if (admin == null) {
            throw new ResourceNotFoundException("Admin { id=" + id + " } not found");
        }
        return admin;
    }

    public List<Admin> listAdmins() throws ResourceNotFoundException {
        List<Admin> adminList;
        adminList = adminRepository.findAll();
        if (adminList.isEmpty()) {
            throw new ResourceNotFoundException("Admin list is empty");
        }
        return adminList;
    }

    @Transactional
    public Admin insertAdmin(Admin admin) throws ResourceConflictException {
        admin.setId(null);
        admin.getUser().setId(null);
        if (userRepository.existsByEmail(admin.getUser().getEmail())) {
            throw new ResourceConflictException("User with  { email='" + admin.getUser().getEmail() + "' } already exists");
        }
        if (userRepository.existsByDni(admin.getUser().getDni())) {
            throw new ResourceConflictException("User with  { dni='" + admin.getUser().getDni() + "' } already exists");
        }
        return adminRepository.save(admin);
    }

    @Transactional
    public Admin updateAdmin(Integer id, Admin admin) throws ResourceNotFoundException, ResourceConflictException {
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
        userRepository.save(admin.getUser()); //lookup for CascadeType
        return adminRepository.save(admin);
    }

    @Transactional
    public void deleteAdmin(Integer id) throws ResourceNotFoundException {
        Admin admin = getAdminById(id);
        adminRepository.delete(admin);
        userRepository.delete(admin.getUser());
    }

    public Admin loginAdmin(String dni, String password) throws LoginException {
        Admin admin;
        admin = adminRepository.findByUserDni(dni).orElse(null);
        if (admin == null) {
            throw new LoginException("Invalid email or password");
        }
        if (!admin.getUser().getPassword().equals(password)) {
            throw new LoginException("Invalid email or password");
        }
        return admin;
    }
}
