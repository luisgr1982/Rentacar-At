package com.app.Rentacar.tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import com.app.Rentacar.exceptions.DateFormatNoValidException;

public class tools {

	//Metodo que convierte una fecha de String a LocalDate
	public static final LocalDate convertStringToLocalDate(String date) throws DateFormatNoValidException { 	
		final LocalDate dateConvert;
		String regexp = "\\d{1,2}/\\d{1,2}/\\d{4}";
		if (Pattern.matches(regexp, date)) {
			dateConvert = LocalDate.parse(date, DateTimeFormatter.ofPattern(Constants.PATTERN_DATE));
		}
		else {
			throw new DateFormatNoValidException();
		}
		return dateConvert;
	}
}