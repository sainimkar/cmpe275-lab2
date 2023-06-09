package com.cmpe275.Lab2.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;



@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class AssociatedEmployerDetailsDto {
    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;
}
