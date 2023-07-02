package br.com.doardin.myexpenses.annotations.fieldmatch;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class MatchFieldsValidator implements ConstraintValidator<MatchFields, Object> {

    private String[] fields;

    @Override
    public void initialize(final MatchFields constraint) {
        this.fields = constraint.fields();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean isValid = false;
        var message = new StringBuilder();

        try {
            for (var i = 0; i < fields.length; i++) {

                var current = BeanUtils.getProperty(value, fields[i]);
                message.append(fields[i]);

                if ((i + 1) < fields.length) {
                    message.append(", ");
                    var next = BeanUtils.getProperty(value, fields[i + 1]);
                    isValid = ((current == null && next == null) || (current != null && current.equals(next)));
                }

            }

        } catch (final Exception ignore) {
            log.error("", ignore);
        }

        if (!isValid) {
            context.buildConstraintViolationWithTemplate(message.append(" fields do not match").toString())
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return isValid;
    }

}
