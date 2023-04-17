package com.cmpe275.Lab2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
//@Embeddable
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "EMPLOYER_TBL")
public class Employer {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Embedded
    private Address address;

    @OneToOne(mappedBy = "employer")
    private Employee employee;

    // constructors, getters/setters
}


