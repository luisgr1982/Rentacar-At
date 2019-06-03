package com.app.Rentacar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.app.Rentacar.tools.Constants;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CarNotAsignRateException extends Exception {

	/**
	 * Excepcion si salta cuando intentamos hacer un alquiler de un vehiculo que no tiene asignado ninguna tarifa.
	 */
	private static final long serialVersionUID = 5521279550124934373L;

	private static final String message = Constants.CAR_NOT_ASIGN_RATE_MESSAGE;
	
	public CarNotAsignRateException() {
		super(message);
	}
}
