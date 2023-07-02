package br.com.doardin.myexpenses.annotations.uniqueemail;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueEmailSubsetValidator.class)
public @interface UniqueEmailSubset {

    String message() default "Already exists an account with given email.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
