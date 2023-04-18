package com.cmpe275.Lab2.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@Data
@NoArgsConstructor

public class ErrorResponseDto {

    @JsonProperty("error_code")
    private String responseCode;

    @JsonProperty("error_message")
    private String responseMessage;

    @JsonProperty("reference")
    private String reference;
}
