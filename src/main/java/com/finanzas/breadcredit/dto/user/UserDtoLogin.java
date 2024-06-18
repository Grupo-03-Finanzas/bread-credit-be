package com.finanzas.breadcredit.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoLogin implements Serializable {
    String dni;
    String password;
}