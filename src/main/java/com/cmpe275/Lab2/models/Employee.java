package com.cmpe275.Lab2.models;

import jakarta.persistence.*;
import lombok.*;
import org.aspectj.lang.annotation.RequiredTypes;
import java.io.Serializable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@IdClass(CompositeKey.class)
@Table(name = "EMPLOYEE_TBL")
public class Employee implements Serializable  {

    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private long id;

    @Id
    @Column(name = "employerId")
    private String employerId; // part of the primary key

    @NotNull
    @Column(name= "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
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

    // TODO
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

    public void update(final Employee fromEmployee) {
        if (Objects.nonNull(fromEmployee.getName())) {
            this.setName(fromEmployee.getName());
        }
        if (Objects.nonNull(fromEmployee.getEmail())) {
            this.setEmail(fromEmployee.getEmail());
        }
        if (Objects.nonNull(fromEmployee.getTitle())) {
            this.setTitle(fromEmployee.getTitle());
        }
        if (Objects.nonNull(fromEmployee.getAddress())) {
            Address newAddress = Address.builder()
                    .street(fromEmployee.getAddress().getStreet())
                    .city(fromEmployee.getAddress().getCity())
                    .state(fromEmployee.getAddress().getState())
                    .zip(fromEmployee.getAddress().getZip())
                    .build();
            this.setAddress(newAddress);
        }
    }

    public void removeCollaborators() {

        getCollaborators().forEach(
                collaborator -> collaborator.removeCollaborator(this)
        );

        getCollaborators().clear();
    }

    public void removeCollaborator(final Employee collaborator) {
        this.getCollaborators().remove(collaborator);
    }

    public void addCollaborator(final Employee collaborator) {
        if (!this.getCollaborators().contains(collaborator))
            this.getCollaborators().add(collaborator);
    }

    public Employee(Employee employee) {
        this.id = employee.getId();
        this.employerId = employee.getEmployerId();
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.title = employee.getTitle();
        this.address = employee.getAddress();
        this.employer = employee.getEmployer();
        this.manager = employee.getManager();
        this.reports = employee.getReports();
        this.collaborators = new ArrayList<>();
        for (Employee emp: employee.getCollaborators()) {
            this.collaborators.add(emp);
        }
    }

    // constructors, getters/setters
}