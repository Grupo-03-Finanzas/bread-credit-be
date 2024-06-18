package com.finanzas.breadcredit.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.User}
 */
@Data
public class UserDtoData implements Serializable {
    String firstName;
    String lastName;
    String dni;
    String phone;
    String email;
}