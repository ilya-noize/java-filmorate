package ru.yandex.practicum.filmorate.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = {LoginWithValidator.class})
public @interface LoginWith {
    String value() default "qwertyuiopasdfghjklzxcvbnm1234567890-_.";

    String message() default "login содержит некорректные символы";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}