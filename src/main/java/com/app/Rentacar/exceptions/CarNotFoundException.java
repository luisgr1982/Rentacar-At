package com.app.Rentacar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.app.Rentacar.tools.Constants;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CarNotFoundException extends Exception {

	/**
	 * 
	 * Excepción si no se localiza un coche en base de datos.
	 * 
	 * 
	 */
	private static final long serialVersionUID = -1775570282919748775L;
	
	private static final String message = Constants.CAR_NOT_FOUND_MESSAGE;

	public CarNotFoundException() {
		super(message);
	}
}
