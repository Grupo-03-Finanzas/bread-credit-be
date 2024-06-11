package com.finanzas.breadcredit.business;

import com.finanzas.breadcredit.entity.Creditaccount;
import com.finanzas.breadcredit.repository.CreditaccountRepository;
import com.finanzas.breadcredit.repository.CustomerRepository;
import com.finanzas.breadcredit.repository.AdminRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditaccountBusiness {

    @Autowired
    private CreditaccountRepository creditaccountRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AdminRepository adminRepository;

    public Creditaccount insertCreditaccount(Creditaccount creditaccount) throws Exception{
        creditaccount.setId(null);
        if(!adminRepository.existsById(creditaccount.getAdmin().getId())) {
            throw new Exception("Admin not found");
        }
        if(!customerRepository.existsById(creditaccount.getCustomer().getId())) {
            throw new Exception("Customer not found");
        }
        if(creditaccountRepository.existsByCustomerIdAndAdminId(creditaccount.getCustomer().getId(), creditaccount.getAdmin().getId())) {
            throw new Exception("A Creditaccount already exists for the specified Admin and Customer");
        }

        return creditaccountRepository.save(creditaccount);
    }

    public Creditaccount getCreditaccountById(Integer id) throws Exception{
        return creditaccountRepository.findById(id).orElseThrow(() -> new Exception("CreditAccount not found"));
    }

    public List<Creditaccount> listCreditaccounts() throws Exception{
        List<Creditaccount> creditaccountList = creditaccountRepository.findAll();
        //if (creditaccountList.isEmpty()) {
        //    throw new Exception("No creditaccounts found");
        //}
        return creditaccountList;
    }

    @Transactional
    public Creditaccount updateCreditaccount(Integer id,Creditaccount creditaccount) throws Exception{
        creditaccount.setId(id);

        if(!creditaccountRepository.existsById(id)) {
            throw new Exception("Creditaccount not found");
        }
        if(!adminRepository.existsById(creditaccount.getAdmin().getId())) {
            throw new Exception("Admin not found");
        }
        if(!customerRepository.existsById(creditaccount.getCustomer().getId())) {
            throw new Exception("Customer not found");
        }
        Creditaccount creditaccountExists = creditaccountRepository.findById(id).orElse(new Creditaccount());
        if(!creditaccountExists.getAdmin().getId().equals(creditaccount.getAdmin().getId()) || !creditaccountExists.getCustomer().getId().equals(creditaccount.getCustomer().getId())) {
            if(creditaccountRepository.existsByCustomerIdAndAdminId(creditaccount.getCustomer().getId(),creditaccount.getAdmin().getId())) {
                throw new Exception("Customer already exists for the specified Admin and Customer");
            }
        }

        return creditaccountRepository.save(creditaccount);
    }

    @Transactional
    public void deleteCreditaccount(Integer id) throws Exception{
        Creditaccount creditaccount = creditaccountRepository.findById(id).orElseThrow(() -> new Exception("Creditaccount not found"));
        creditaccountRepository.delete(creditaccount);
    }

    public List<Creditaccount> getCreditAccountByAdminId(Integer id) throws Exception{
        List<Creditaccount> creditaccountList = creditaccountRepository.findByAdmin_Id(id).orElseThrow();
        return creditaccountList;
    }

    public Creditaccount getCreditaccountByAdminIdANDCustomerId(Integer adminId, Integer customerId) throws Exception{
        return creditaccountRepository.findByAdmin_IdAndCustomer_Id(adminId, customerId).orElseThrow();
    }
}
