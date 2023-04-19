package com.cmpe275.Lab2.mapper;

import com.cmpe275.Lab2.model.response.*;
import com.cmpe275.Lab2.models.Address;
import com.cmpe275.Lab2.models.Employee;
import com.cmpe275.Lab2.models.Employer;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public static long generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        long uniqueId = uuid.getMostSignificantBits() & Long.MAX_VALUE;
        return uniqueId;
    }

    public Employee map(final Map<String, String> params) {
        return Employee.builder()
                .id(generateUniqueId())
                .name(params.get("name"))
                .employerId(params.get("employerId"))
                .email(params.get("email"))
                .title(params.get("title"))
                .address(Address.builder()
                        .street(params.get("street"))
                        .city(params.get("city"))
                        .state(params.get("state"))
                        .zip(params.get("zip"))
                        .build())
                .build();
    }

    public EmployeeDto map(final Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .employerId(employee.getEmployerId())
                .name(employee.getName())
                .email(employee.getEmail())
                .title(employee.getTitle())
                .address(mapAddress(employee.getAddress()))
                .employer(mapEmployer(employee.getEmployer()))
                .manager(mapManager(employee.getManager()))
                .reports(mapReports(employee.getReports()))
                .collaborators(mapCollaborators(employee))
                .build();
    }

    private AddressDto mapAddress(final Address address) {
        if (Objects.isNull(address)) {
            return AddressDto.builder().build();
        }
        return AddressDto.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .zip(address.getZip())
                .build();
    }

    private AssociatedEmployerDetailsDto mapEmployer(final Employer employer) {
        return AssociatedEmployerDetailsDto.builder()
                .id(employer.getId())
                .name(employer.getName())
                .build();
    }

    private AssociatedEmployeeDetailsDto mapManager(final Employee manager) {
        if (Objects.isNull(manager)) {
            return AssociatedEmployeeDetailsDto.builder().build();
        }

        return AssociatedEmployeeDetailsDto.builder()
                .id(manager.getId())
                .name(manager.getName())
                .title(manager.getTitle())
                .build();
    }

    private List<AssociatedEmployeeDetailsDto> mapReports(final List<Employee> employees) {
        return Objects.nonNull(employees)
                ? employees
                .stream()
                .map(report -> AssociatedEmployeeDetailsDto.builder()
                        .id(report.getId())
                        .name(report.getName())
                        .title(report.getTitle())
                        .build()
                )
                .collect(Collectors.toList())
                : new ArrayList<>();
    }

    private List<CollaboratorDto> mapCollaborators(final Employee employee) {
        List<CollaboratorDto> collaborators = Objects.nonNull(employee.getCollaborators())
                ? employee.getCollaborators()
                .stream()
                .map(collaborator -> CollaboratorDto.builder()
                        .id(collaborator.getId())
                        .name(collaborator.getName())
                        .title(collaborator.getTitle())
                        .employer(
                                AssociatedEmployerDetailsDto.builder()
                                        .id(collaborator.getEmployer().getId())
                                        .name(collaborator.getEmployer().getName())
                                        .build()
                        )
                        .build()
                )
                .collect(Collectors.toList()) : new ArrayList<>();
        return collaborators;
    }
}
