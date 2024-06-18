package com.finanzas.breadcredit.dto.customer;

import com.finanzas.breadcredit.dto.user.UserDtoData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Customer}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDtoData implements Serializable {
    Long id;
    UserDtoData user;
    String address;
}
