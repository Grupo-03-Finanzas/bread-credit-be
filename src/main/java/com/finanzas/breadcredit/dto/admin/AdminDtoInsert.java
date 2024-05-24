package com.finanzas.breadcredit.dto.admin;

import com.finanzas.breadcredit.dto.user.UserDtoInsert;
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
public class AdminDtoInsert implements Serializable {
    Integer id;
    UserDtoInsert user;
    String businessName;
    String businessType;
}