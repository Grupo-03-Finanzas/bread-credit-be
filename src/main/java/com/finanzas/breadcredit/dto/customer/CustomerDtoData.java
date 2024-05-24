package com.finanzas.breadcredit.dto.customer;

import com.finanzas.breadcredit.dto.user.UserDtoInsert;
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
public class CustomerDtoData implements Serializable{
    Integer id;
    UserDtoInsert user;
    String address;
}
