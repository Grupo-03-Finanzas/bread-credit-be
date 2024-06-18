package com.finanzas.breadcredit.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.finanzas.breadcredit.entity.Admin}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDtoInsert implements Serializable {
    private Long id;
    private UserDto user;
    private String businessName;
    private String businessType;

    /**
     * DTO for {@link com.finanzas.breadcredit.entity.User}
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDto implements Serializable {
        private String firstName;
        private String lastName;
        private String dni;
        private String phone;
        private String email;
        private String password;
    }
}