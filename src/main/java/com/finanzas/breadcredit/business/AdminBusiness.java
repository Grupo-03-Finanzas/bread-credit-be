package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Admin;
import com.finanzas.breadcredit.repository.AdminRepository;
import com.finanzas.breadcredit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminBusiness {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;

    public Admin insertAdmin(Admin adminSubmitted) throws Exception{
        if (adminRepository.findByUserEmail(adminSubmitted.getUser().getEmail()) != null) {
            throw new Exception("Email already exists");
        }
        if (adminRepository.findByUserDni(adminSubmitted.getUser().getEmail()) != null) {
            throw new Exception("Dni already exists");
        }
        return adminRepository.save(adminSubmitted);
    }

    public List<Admin> listAdmins() {
        return adminRepository.findAll();
    }

    public Admin updateAdmin(Admin adminSubmitted) throws Exception{
        System.out.println("Fernando 3: " +  adminSubmitted);
        if (adminSubmitted.getId() == null){
            throw new Exception("ID not present");
        }
        userRepository.save(adminSubmitted.getUser());
        return adminRepository.save(adminSubmitted);
    }

    public void deleteAdmin(Integer id) throws Exception{
        Admin admin = adminRepository.findAdminById(id);
        if (admin == null){
            throw new Exception("Admin not found");
        }
        adminRepository.delete(admin);
    }
}
