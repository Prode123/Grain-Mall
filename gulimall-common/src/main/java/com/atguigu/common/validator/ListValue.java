package com.atguigu.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Description
 * @Author lt
 * @Data 2024/3/24 20:59
 */

@Documented
@Constraint(validatedBy = {ListValueConstraintValidator.class} )
@Target({METHOD,FIELD,ANNOTATION_TYPE,CONSTRUCTOR,PARAMETER,TYPE_USE})
@Retention(RUNTIME)
public @interface ListValue {
    String message() default "{com.atguigu.common.validator.ListValue.message}";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] vals() default {};
}
