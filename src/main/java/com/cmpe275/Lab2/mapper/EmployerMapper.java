package com.cmpe275.Lab2.mapper;

import com.cmpe275.Lab2.model.response.AddressDto;
import com.cmpe275.Lab2.model.response.EmployerDto;
import com.cmpe275.Lab2.models.Address;
import com.cmpe275.Lab2.models.Employer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class EmployerMapper {
    public Employer map(final Map<String, String> params) {
        return Employer.builder()
                .name(params.get("name"))
                .description(params.get("description"))
                .address(Address.builder()
                        .street(params.get("street"))
                        .city(params.get("city"))
                        .state(params.get("state"))
                        .zip(params.get("zip"))
                        .build())
                .build();
    }

    public EmployerDto map(final Employer employer) {
        return EmployerDto.builder()
                .id(employer.getId())
                .name(employer.getName())
                .description(employer.getDescription())
                .address(mapAddress(employer.getAddress()))
                .build();
    }
//
    private AddressDto mapAddress(final Address address) {
        return Objects.nonNull(address)
                ? AddressDto.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .zip(address.getZip())
                .build()
                : AddressDto.builder().build();
    }
}