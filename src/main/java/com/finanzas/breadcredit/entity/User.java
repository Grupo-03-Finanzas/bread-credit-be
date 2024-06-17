package com.finanzas.breadcredit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @SequenceGenerator(name = "user_iduser_seq", sequenceName = "user_iduser_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_iduser_seq")
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 32)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 32)
    private String lastName;
    @Column(name = "dni", nullable = false, length = 8, unique = true)
    private String dni;
    @Column(name = "phone", nullable = false, length = 9)
    private String phone;
    @Column(name = "email", nullable = false, length = 32, unique = true)
    private String email;
    @Column(name = "password", nullable = false, length = 32)
    private String password;

}