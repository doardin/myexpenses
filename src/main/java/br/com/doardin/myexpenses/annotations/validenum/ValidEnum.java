package br.com.doardin.myexpenses.annotations.validenum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Constraint(validatedBy = { EnumValidator.class })
public @interface ValidEnum {

    String message() default "";

    String[] fields() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};

    Class<? extends Enum<?>> enumClass();

}
