package br.com.doardin.myexpenses.annotations.uniqueemail;

import br.com.doardin.myexpenses.domain.user.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueEmailSubsetValidator implements ConstraintValidator<UniqueEmailSubset, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return true;
        return (!this.userRepository.existsByEmail(value));
    }

}
