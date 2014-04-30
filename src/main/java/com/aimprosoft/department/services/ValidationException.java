package com.aimprosoft.department.services;

import java.util.Map;


public class ValidationException extends ServiceException {
    private final Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
