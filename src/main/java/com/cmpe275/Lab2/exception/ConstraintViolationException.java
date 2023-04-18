package com.cmpe275.Lab2.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ConstraintViolationException extends RuntimeException {
    private final String ERROR_CODE = "CONSTRAINT_VIOLATION";

    private String parameter;

    public ConstraintViolationException(final String message, final String parameter) {
        super(message);
        this.parameter = parameter;
    }
}
