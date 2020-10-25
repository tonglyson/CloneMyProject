package com.example.clonemyproject.customanotation;

import com.example.clonemyproject.entities.OutputInfo;

import javax.transaction.Transactional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckQuantity implements
        ConstraintValidator<QuantityValidator, OutputInfo> {

    @Override
    public void initialize(QuantityValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(OutputInfo outputInfo, ConstraintValidatorContext constraintValidatorContext) {

        if(outputInfo.getInputInfo()!=null && !(outputInfo.getQuantity() <= outputInfo.getInputInfo().getQuantity())) {

            return false;
        }
        return true;
    }
}