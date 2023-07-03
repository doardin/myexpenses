package br.com.doardin.myexpenses.annotations.validenum;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValidEnum, CharSequence> {

    private List<String> acceptedValues;

    @Override
    public void initialize(ValidEnum constraint) {
        acceptedValues = Stream.of(constraint
                .enumClass()
                .getEnumConstants()).map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        boolean isValid = value == null || acceptedValues.contains(value.toString());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "This field is accepted only with the following values: "
                            + acceptedValues.stream().collect(Collectors.joining("|")))
                    .addConstraintViolation();
        }

        return isValid;
    }

}