package com.finanzas.breadcredit.dto.admin;

import com.finanzas.breadcredit.dto.user.UserDtoData;
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
public class AdminDtoData implements Serializable {
    Integer id;
    UserDtoData user;
    String businessName;
    String businessType;
}