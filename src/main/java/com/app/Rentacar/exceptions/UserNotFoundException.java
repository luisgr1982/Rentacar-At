package com.app.Rentacar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.app.Rentacar.tools.Constants;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception  {

	/**
	 * 
	 * Excepcion si no encuentra el usuario en base de datos.
	 * 
	 */
	private static final long serialVersionUID = -7538162748878298114L;
	
	private static final String message = Constants.USER_NOT_FOUND_MESSAGE; 
	
	public UserNotFoundException() {
		super(message);
	}

}
