package com.app.Rentacar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.app.Rentacar.tools.Constants;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RentNotFoundException extends RuntimeException{
	
	/**
	 * Excepcion que salta al no encontrar un alquiler en base de datos.
	 */
	private static final long serialVersionUID = 3767433784904893945L;

	private static final String message = Constants.RENT_NOT_FOUND_MESSAGE;

	public RentNotFoundException() {
		super(message);
	}
}
