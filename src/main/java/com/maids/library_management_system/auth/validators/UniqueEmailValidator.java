package com.maids.library_management_system.auth.validators;

import com.maids.library_management_system.auth.constraints.UniqueEmail;
import com.maids.library_management_system.auth.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (email == null) return true;
        return userRepository.findByEmail(email).isEmpty();

    }
}
