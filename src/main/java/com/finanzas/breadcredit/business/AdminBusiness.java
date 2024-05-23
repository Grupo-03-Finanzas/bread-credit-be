package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Admin;
import com.finanzas.breadcredit.repository.AdminRepository;
import com.finanzas.breadcredit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminBusiness {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;


    public Admin insertAdmin(Admin admin) throws Exception {
        if(userRepository.existsByEmail(admin.getUser().getEmail())) {
            throw new Exception("Email Already Exists");
        }
        if(userRepository.existsByDni(admin.getUser().getDni())) {
            throw new Exception("Dni Already Exists");
        }

        return adminRepository.save(admin);
    }

    public Admin getAdminById(Integer id) throws Exception{
        return adminRepository.findById(id).orElseThrow(() -> new Exception("Admin not found"));
    }

    public List<Admin> listAdmins() throws Exception {
        List<Admin> adminList = adminRepository.findAll();

        //if (adminList.isEmpty()) {
        //    throw new Exception("Admins not found");
        //}

        return adminList;
    }

    @Transactional
    public Admin updateAdmin(Integer id, Admin admin) throws Exception {
        admin.setId(id);
        admin.getUser().setId(id);

        if (!adminRepository.existsById(admin.getId())) {
            throw new Exception("Admin not found");
        }
        Admin adminExists = adminRepository.findById(admin.getId()).orElse(new Admin());
        if (!adminExists.getUser().getEmail().equals(admin.getUser().getEmail())) {
            if(userRepository.existsByEmail(admin.getUser().getEmail())) {
                throw new Exception("Email Already Exists");
            }
        }
        if (!adminExists.getUser().getDni().equals(admin.getUser().getDni())) {
            if(userRepository.existsByDni(admin.getUser().getDni())) {
                throw new Exception("Dni Already Exists");
            }
        }

        userRepository.save(admin.getUser());
        return adminRepository.save(admin);
    }

    @Transactional
    public void deleteAdmin(Integer id) throws Exception {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new Exception("Admin not found"));
        adminRepository.delete(admin);
        userRepository.delete(admin.getUser());
    }
}
