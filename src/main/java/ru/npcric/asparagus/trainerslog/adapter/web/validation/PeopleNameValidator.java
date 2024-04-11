package ru.npcric.asparagus.trainerslog.adapter.web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PeopleNameValidator implements ConstraintValidator<PeopleNameConstraint, String> {
    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        if (name.matches("[a-zA-Z]+ [a-zA-Z]+ [a-zA-Z]+")||name.matches("[а-яА-Я]+ [а-яА-Я]+ [а-яА-Я]+")){
            return true;
        }
        return false;
    }
}
