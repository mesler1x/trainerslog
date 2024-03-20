package ru.npcric.asparagus.trainerslog.adapter.web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GroupNameValidator implements ConstraintValidator<GroupNameConstraint, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.matches("\\b[A-Z]{2}-\\d{2}\\b")){
            return true;
        }
        return false;
    }
}
