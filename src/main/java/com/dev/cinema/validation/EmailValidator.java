package com.dev.cinema.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {
    private static final String REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    @Override
    public void initialize(EmailValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext cxt) {
        return email != null && email.matches(REGEX);
    }
}
