package io.github.mennakhalilselim.taskmanagementsystem.validation.validator;

import io.github.mennakhalilselim.taskmanagementsystem.respository.UserRepository;
import io.github.mennakhalilselim.taskmanagementsystem.validation.annotation.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(email)) {
            return true;
        }
        return !userRepository.existsByEmail(email);
    }
}
