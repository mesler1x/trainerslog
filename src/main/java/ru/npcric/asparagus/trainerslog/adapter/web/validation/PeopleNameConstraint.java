package ru.npcric.asparagus.trainerslog.adapter.web.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PeopleNameValidator.class)
@Target(ElementType.FIELD)
public @interface PeopleNameConstraint {
    String message() default "ФИО должно быть в формате: Алексеев Алексей Алексеевич";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
