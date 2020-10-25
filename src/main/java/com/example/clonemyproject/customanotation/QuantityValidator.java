package com.example.clonemyproject.customanotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckQuantity.class)
@Documented
public @interface QuantityValidator {
    String message () default "total price must be 50 or greater for online order. ";

    Class<?>[] groups () default {};
    Class<? extends Payload>[] payload () default {};

    @interface List {
        QuantityValidator[] value();
    }

}