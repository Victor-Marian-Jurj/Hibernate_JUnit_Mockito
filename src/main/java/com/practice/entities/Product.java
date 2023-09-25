package com.practice.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "bar_code")
    private String barCode;

    @Column(name = "cnp_buyer")
    private String cnpBuyer;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "quantity")
    private int quantity;

    private int price;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "cnp_buyer")
    private Person person;
}
