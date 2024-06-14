package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Creditaccount;
import com.finanzas.breadcredit.exception.ResourceConflictException;
import com.finanzas.breadcredit.exception.ResourceNotFoundException;
import com.finanzas.breadcredit.repository.AdminRepository;
import com.finanzas.breadcredit.repository.CreditaccountRepository;
import com.finanzas.breadcredit.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditaccountBusiness {

    private final CreditaccountRepository creditaccountRepository;
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public CreditaccountBusiness(CreditaccountRepository creditaccountRepository, CustomerRepository customerRepository, AdminRepository adminRepository) {
        this.creditaccountRepository = creditaccountRepository;
        this.customerRepository = customerRepository;
        this.adminRepository = adminRepository;
    }

    public Creditaccount getCreditaccountById(Integer id) throws ResourceNotFoundException {
        Creditaccount creditaccount = creditaccountRepository.findById(id).orElse(null);
        if (creditaccount == null) {
            throw new ResourceNotFoundException("Credit account { id=" + id + " } not found");
        }
        return creditaccount;
    }

    public List<Creditaccount> listCreditaccounts() throws ResourceNotFoundException {
        List<Creditaccount> creditaccountList = creditaccountRepository.findAll();
        if (creditaccountList.isEmpty()) {
            throw new ResourceNotFoundException("Credit account list is empty");
        }
        return creditaccountList;
    }

    public Creditaccount insertCreditaccount(Creditaccount creditaccount) throws ResourceNotFoundException, ResourceConflictException {
        creditaccount.setId(null);
        if (!adminRepository.existsById(creditaccount.getAdmin().getId())) {
            throw new ResourceNotFoundException("Admin { id=" + creditaccount.getAdmin().getId() + "} not found");
        }
        if (!customerRepository.existsById(creditaccount.getCustomer().getId())) {
            throw new ResourceNotFoundException("Customer { id=" + creditaccount.getCustomer().getId() + " } not found");
        }
        if (creditaccountRepository.existsByCustomer_IdAndAdmin_Id(creditaccount.getCustomer().getId(), creditaccount.getAdmin().getId())) {
            throw new ResourceConflictException("Credit account already exists");
        }
        return creditaccountRepository.save(creditaccount);
    }
    @Transactional
    public Creditaccount updateCreditaccount(Integer id, Creditaccount creditaccount) throws ResourceNotFoundException, ResourceConflictException {
        creditaccount.setId(id);
        if (!creditaccountRepository.existsById(id)) {
            throw new ResourceNotFoundException("Credit account { id=" + id + " } not found");
        }
        if (!adminRepository.existsById(creditaccount.getAdmin().getId())) {
            throw new ResourceNotFoundException("Admin { id=" + creditaccount.getAdmin().getId() + "} not found");
        }
        if (!customerRepository.existsById(creditaccount.getCustomer().getId())) {
            throw new ResourceNotFoundException("Customer { id=" + creditaccount.getCustomer().getId() + " } not found");
        }
        Creditaccount creditaccountExists = creditaccountRepository.findById(id).orElse(new Creditaccount());
        if (!creditaccountExists.getAdmin().getId().equals(creditaccount.getAdmin().getId()) || !creditaccountExists.getCustomer().getId().equals(creditaccount.getCustomer().getId())) {
            if (creditaccountRepository.existsByCustomer_IdAndAdmin_Id(creditaccount.getCustomer().getId(), creditaccount.getAdmin().getId())) {
                throw new ResourceConflictException("Credit account for Admin { id=" + creditaccount.getAdmin().getId() + " } and Customer { id=" + creditaccount.getCustomer().getId() + " } already exists");
            }
        }
        return creditaccountRepository.save(creditaccount);
    }

    @Transactional
    public void deleteCreditaccount(Integer id) throws ResourceNotFoundException {
        Creditaccount creditaccount = creditaccountRepository.findById(id).orElse(null);
        if (creditaccount == null) {
            throw new ResourceNotFoundException("Credit account { id=" + id + " } not found");
        }
        creditaccountRepository.delete(creditaccount);
    }

    public List<Creditaccount> getCreditAccountsByAdminId(Integer id) throws ResourceNotFoundException {
        List<Creditaccount> creditaccountList = creditaccountRepository.findByAdmin_Id(id).orElse(new ArrayList<>());
        if (creditaccountList.isEmpty()) {
            throw new ResourceNotFoundException("Credit account list is empty");
        }
        return creditaccountList;
    }

    public Creditaccount getCreditaccountByAdmin_IdANDCustomer_Id(Integer adminId, Integer customerId) throws ResourceNotFoundException {
        Creditaccount creditaccount = creditaccountRepository.findByAdmin_IdAndCustomer_Id(adminId, customerId).orElse(null);
        if (creditaccount == null) {
            throw new ResourceNotFoundException("Credit account not found");
        }
        return creditaccount;
    }

    public Creditaccount getCreditaccountByCustomer_Id(Integer customerId) throws ResourceNotFoundException {
        Creditaccount creditaccount = creditaccountRepository.findByCustomer_Id(customerId).orElse(null);
        if (creditaccount == null) {
            throw new ResourceNotFoundException("Credit account not found");
        }
        return creditaccount;
    }

    public Creditaccount getCreditaccountByCustomerDni(String dni) throws ResourceNotFoundException {
        Creditaccount creditaccount = creditaccountRepository.findByCustomer_User_Dni(dni).orElse(null);
        if (creditaccount == null) {
            throw new ResourceNotFoundException("Credit account not found");
        }
        return creditaccount;
    }

}
