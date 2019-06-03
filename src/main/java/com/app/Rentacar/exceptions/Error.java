package com.app.Rentacar.exceptions;

import java.util.Date;


import lombok.Data;

@Data
public class Error {
	
	private Date timestamp;
	private String message;
	private String description;
	
	public Error(Date timestamp, String message, String description) {
		super();
		this.timestamp = timestamp;		
		this.message = message;
		this.description = description;
	}
}
