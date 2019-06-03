package com.app.Rentacar.exceptions;

import com.app.Rentacar.tools.Constants;

public class DateFormatNoValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9069102274625948747L;
	
	private static final String message = Constants.DATE_FORMAT_NO_VALID_MESSAGE;
	
	public DateFormatNoValidException() {
		super(message);
	}

}
