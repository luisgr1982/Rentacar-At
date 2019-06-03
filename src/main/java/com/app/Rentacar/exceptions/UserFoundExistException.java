package com.app.Rentacar.exceptions;

import com.app.Rentacar.tools.Constants;

public class UserFoundExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2801461893349902036L;
	
	private static final String message = Constants.USER_FOUND_EXIST_MESSAGE;
	
	public UserFoundExistException() {
		super(message);
	}

}
