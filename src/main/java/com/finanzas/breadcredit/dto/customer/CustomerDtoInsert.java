package com.finanzas.breadcredit.dto.customer;

import com.finanzas.breadcredit.dto.user.UserDtoInsert;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Customer}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDtoInsert implements Serializable {
    Integer id;
    UserDtoInsert user;
    String address;
}
