package com.cmpe275.Lab2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "employer")
    private List<Employee> employeeList;

    public void update(Employer fromEmployer) {
        if (Objects.nonNull(fromEmployer.getName())) {
            this.setName(fromEmployer.getName());
        }
        if (Objects.nonNull(fromEmployer.getDescription())) {
            this.setDescription(fromEmployer.getDescription());
        }
        if (Objects.nonNull(fromEmployer.getAddress())) {
            Address newAddress = Address.builder()
                    .street(fromEmployer.getAddress().getStreet())
                    .city(fromEmployer.getAddress().getCity())
                    .state(fromEmployer.getAddress().getState())
                    .zip(fromEmployer.getAddress().getZip())
                    .build();
            this.setAddress(newAddress);
        }
    }
// constructors, getters/setters
}


