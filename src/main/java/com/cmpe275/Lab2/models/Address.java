package com.cmpe275.Lab2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Address {

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;
}