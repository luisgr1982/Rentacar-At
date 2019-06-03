package com.app.Rentacar.exceptions;

import com.app.Rentacar.tools.Constants;

public class RateFoundExistException extends Exception {

	/**
	 * 
	 * Excepcion que salta cuando intententamos guardar una tarifa con un id que ya existe.
	 * 
	 */
	private static final long serialVersionUID = -2362543169773879323L;
	
	private static final String message = Constants.RATE_FOUND_EXIST_MESSAGE;
	
	public RateFoundExistException() {
		super(message);
	}

}
