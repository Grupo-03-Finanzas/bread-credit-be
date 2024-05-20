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

    public Admin insertAdmin(Admin adminIn) throws Exception {
        if (userRepository.findUserByEmail(adminIn.getUser().getEmail()) != null) {
            throw new Exception("Email already exists");
        }
        if (userRepository.findUserByDni(adminIn.getUser().getDni()) != null) {
            throw new Exception("Dni already exists");
        }
        return adminRepository.save(adminIn);
    }

    public Admin listAdminById(Integer id) {
        return adminRepository.findAdminById(id);
    }

    public List<Admin> listAdmins() {
        return adminRepository.findAll();
    }

    public Admin updateAdmin(Admin adminIn) throws Exception {
        Admin adminCurrent = adminRepository.findAdminById(adminIn.getId());
        if (userRepository.findUserByEmail(adminIn.getUser().getEmail()) != null) {
            if(!userRepository.findUserByEmail(adminIn.getUser().getEmail()).getId().equals(adminCurrent.getUser().getId())){
                throw new Exception("Email already exists");
            }
        }
        if (userRepository.findUserByDni(adminIn.getUser().getDni()) != null) {
            if(!userRepository.findUserByDni(adminIn.getUser().getDni()).getId().equals(adminCurrent.getUser().getId()) ){
                throw new Exception("Dni already exists");
            }
        }
        if (adminIn.getId() == null || adminIn.getId() == 0){
            throw new Exception("ID not present");
        }
        userRepository.save(adminIn.getUser());
        return adminRepository.save(adminIn);
    }

    public void deleteAdmin(Integer id) throws Exception{
        Admin admin = adminRepository.findAdminById(id);
        if (admin == null){
            throw new Exception("Admin not found");
        }
        adminRepository.delete(admin);
        userRepository.delete(admin.getUser());
    }
}
