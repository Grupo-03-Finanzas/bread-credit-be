package com.finanzas.breadcredit.dto.user;

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
public class UserDtoData implements Serializable {
    Integer id;
    String firstName;
    String lastName;
    String dni;
    String phone;
    String email;
}