package com.example.newsService.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {RequestFilterValidValidator.class, NewsFilterValidValidator.class})
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestFilterValid {

    String message() default "Поля пагинации должны быть указаны!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
