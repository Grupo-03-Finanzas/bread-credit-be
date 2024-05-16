package com.finanzas.breadcredit.dto;

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
    UserDtoData user;
    String businessName;
    String businessType;
}