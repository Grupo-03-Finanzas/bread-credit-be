package com.finanzas.breadcredit.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "admin_id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    private User user;

    @Column(name = "business_name", nullable = false, length = 32)
    private String businessName;

    @Column(name = "business_type", nullable = false, length = 32)
    private String businessType;

}