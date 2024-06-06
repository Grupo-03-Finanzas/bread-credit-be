package com.finanzas.breadcredit.dto.customer;

import com.finanzas.breadcredit.dto.user.UserDtoLogin;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Customer}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDtoLogin implements Serializable {
    Integer id;
    UserDtoLogin user;
    String address;
}