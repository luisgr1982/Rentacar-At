package com.app.Rentacar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.app.Rentacar.tools.Constants;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RateNotFoundException extends Exception {

	/**
	 * 
	 * Excepcion que salta cuando intentamos localizar una tarifa que no se encuentra en BD.
	 * 
	 */
	private static final long serialVersionUID = -4298094689908696078L;
	
	private static final String message = Constants.RATE_NOT_FOUND_MESSAGE;
	
	public RateNotFoundException() {
		super(message);
	}

}
