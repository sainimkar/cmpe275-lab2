package com.cmpe275.Lab2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(CompositeKey.class)
@Table(name = "EMPLOYEE_TBL")
public class Employee  {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Id
    @Column(name = "employerId")
    private String employerId; // part of the primary key

    @Column(nullable = false)
    private String name;

    private String email;
    private String title;

    @Embedded
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "employerId", referencedColumnName = "employerId"),
            @JoinColumn(name = "id", referencedColumnName = "id")
    })
    private Employer employer;

    @ManyToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "manager_id", referencedColumnName = "employerId")
    private Employee manager;

    @OneToMany
    @JoinColumns({
            @JoinColumn(name = "reports_employerId", referencedColumnName = "employerId"),
            @JoinColumn(name = "reports_id", referencedColumnName = "id")
    })
        private List<Employee> reports;

    @OneToMany
    @JoinColumns({
            @JoinColumn(name = "collaborators_employerId", referencedColumnName = "employerId"),
            @JoinColumn(name = "collaborators_id", referencedColumnName = "id")
    })
    private List<Employee> collaborators;

    // constructors, getters/setters
}