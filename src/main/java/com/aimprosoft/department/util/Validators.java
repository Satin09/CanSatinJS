package com.aimprosoft.department.util;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;
import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Validators {

    public static Map<String, String> validateModel(Object model) {
        Map<String, String> errors = new HashMap();

        Validator validator = new Validator();

        List<ConstraintViolation> violations = validator.validate(model);

        for (ConstraintViolation violation : violations) {
            OValContext context = violation.getContext();
            if (context instanceof FieldContext) {
                FieldContext fieldContext = (FieldContext) context;
                String key = fieldContext.getField().getName();
                errors.put(key, violation.getMessage());
            }
        }

        return errors;
    }

}
