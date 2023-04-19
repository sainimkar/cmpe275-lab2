package com.cmpe275.Lab2.utility;


import com.cmpe275.Lab2.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

public class ValidatorUtil {

//    public static void validateParams(final Map<String, String> params, final List<String> parameters)
//            throws ConstraintViolationException {
//        for (final String parameter : parameters) {
//            if (!params.containsKey(parameter) || StringUtils.isEmpty(params.get(parameter))) {
//                throw new ConstraintViolationException("Query parameter missing in request", parameter);
//            }
//        }
//    }

    public static void validateParams(final Map<String, String> params, final List<String> parameters) {
        try {
            for (final String parameter : parameters) {
                if (!params.containsKey(parameter) || StringUtils.isEmpty(params.get(parameter))) {
                    throw new ConstraintViolationException("Query parameter missing in request", parameter);
                }
            }
        } catch (ConstraintViolationException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    public static void validateRestrictedParam(final Map<String, String> params,
                                               final List<String> restrictedParams)
            throws ConstraintViolationException {
        for (final String parameter : restrictedParams) {
            if (params.containsKey(parameter))
                throw new ConstraintViolationException("Not allowed as query parameter", parameter);
        }

    }
}
