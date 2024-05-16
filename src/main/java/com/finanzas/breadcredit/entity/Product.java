package com.finanzas.breadcredit.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "product")
public class Product {
    @Id
    @SequenceGenerator(name="product_idproduct_seq",sequenceName="product_idproduct_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="product_idproduct_seq")
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Column(name = "price", nullable = false, precision = 16, scale = 2)
    private BigDecimal price;

}