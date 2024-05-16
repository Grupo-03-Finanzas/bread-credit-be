package com.finanzas.breadcredit.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.User}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDtoInsert implements Serializable {
    String firstName;
    String lastName;
    String dni;
    String phone;
    String email;
    String password;
}