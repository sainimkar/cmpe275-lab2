package com.cmpe275.Lab2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "EMPLOYER_TBL")
public class Employer {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Embedded
    private Address address;

    @OneToOne(mappedBy = "employer")
    private Employee employee;

    public long getId() {
        return id;
    }

    public String getName(String name) {
        return this.name;
    }

    public String getDescription(String description) {
        return this.description;
    }

    public Address getAddress() {
        return address;
    }

    public Employee getEmployee() {
        return employee;
    }


// constructors, getters/setters
}


