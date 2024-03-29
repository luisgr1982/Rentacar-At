package com.app.Rentacar.tools;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
 
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
 
import javax.validation.Constraint;
import javax.validation.Payload;
 
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = DniValidator.class)
@Documented
public @interface Dni {
	String message() default Constants.DNI_NO_VALID;
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};	
}