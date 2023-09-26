package com.practice.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Table(name = "persons")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {

    @Id
    @Column(name = "cnp")
    private String cnp;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "adress")
    private String address;

    private String phone;

    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    @ToString.Exclude
    private List<Product> productList;
}
