package com.finanzas.breadcredit.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.User}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoInsert implements Serializable {
    Integer id;
    String firstName;
    String lastName;
    String dni;
    String phone;
    String email;
    String password;
}