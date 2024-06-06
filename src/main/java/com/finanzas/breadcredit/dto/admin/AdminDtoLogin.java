package com.finanzas.breadcredit.dto.admin;

import com.finanzas.breadcredit.dto.user.UserDtoLogin;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Admin}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminDtoLogin implements Serializable {
    Integer id;
    UserDtoLogin user;
    String businessName;
    String businessType;
}