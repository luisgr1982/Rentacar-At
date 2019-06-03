package com.app.Rentacar.exceptions;

import com.app.Rentacar.tools.Constants;

public class CarFoundExistException extends Exception {

	/**
	 * 
	 * Excepción que salta cuando intentamos dar de alta un coche que ya existe en BD.
	 * 
	 */
	private static final long serialVersionUID = 5533359853665272596L;

	private static final String message = Constants.CAR_FOUND_EXIST_MESSAGE;
	
	public CarFoundExistException() {
		super(message);
	}
}
