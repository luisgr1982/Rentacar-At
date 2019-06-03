package com.app.Rentacar.tools;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.app.Rentacar.tools.Constants;

public class DniValidator implements ConstraintValidator<Dni, String> {

	private Pattern pattern = Pattern.compile(Constants.PATTERN_DNI);

	@Override
	public void initialize(Dni constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean isValid= false;
		if (Optional.ofNullable(value).isPresent()) {
			final Matcher matcher = pattern.matcher(value);
			if (matcher.matches()) {
				Integer position = Integer.valueOf(value.substring(0, 8)) % 23;
				isValid= value.substring(8, 9)
						.equalsIgnoreCase(Constants.LETTERS_DNI.substring(position, position + 1));	
			}
		}
		return isValid;
	}
}
