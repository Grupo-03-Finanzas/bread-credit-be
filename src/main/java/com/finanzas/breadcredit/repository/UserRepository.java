package com.finanzas.breadcredit.repository;

import com.finanzas.breadcredit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByEmail(String email);
    User findUserByDni(String dni);
}