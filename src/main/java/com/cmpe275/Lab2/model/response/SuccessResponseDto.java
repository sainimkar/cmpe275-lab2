package com.cmpe275.Lab2.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@Getter
@Data

@NoArgsConstructor
public class SuccessResponseDto {
    @JsonProperty("success_message")
    private String responseMessage;
}
