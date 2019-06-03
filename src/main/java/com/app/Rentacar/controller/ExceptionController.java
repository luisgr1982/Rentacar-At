package com.app.Rentacar.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.app.Rentacar.exceptions.CarFoundExistException;
import com.app.Rentacar.exceptions.CarNotAsignRateException;
import com.app.Rentacar.exceptions.CarNotFoundException;
import com.app.Rentacar.exceptions.DateFormatNoValidException;
import com.app.Rentacar.exceptions.Error;
import com.app.Rentacar.exceptions.RateFoundExistException;
import com.app.Rentacar.exceptions.RateNotFoundException;
import com.app.Rentacar.exceptions.RentNotFoundException;
import com.app.Rentacar.exceptions.UserFoundExistException;
import com.app.Rentacar.exceptions.UserNotFoundException;

@ControllerAdvice
public class ExceptionController {

	private Error errorDetail;

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Error> userNotFoundException(UserNotFoundException ex, WebRequest request) {
		errorDetail = new Error(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Error>(errorDetail, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserFoundExistException.class)
	public ResponseEntity<Error> userFoundExistException(UserFoundExistException ex, WebRequest request) {
		errorDetail = new Error(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Error>(errorDetail, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CarNotFoundException.class)
	public ResponseEntity<Error> carNotFoundException(CarNotFoundException ex, WebRequest request) {
		errorDetail = new Error(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Error>(errorDetail, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CarFoundExistException.class)
	public ResponseEntity<Error> carFoundExistException(CarFoundExistException ex, WebRequest request) {
		errorDetail = new Error(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Error>(errorDetail, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RentNotFoundException.class)
	public ResponseEntity<Error> rentNotFoundException(RentNotFoundException ex, WebRequest request) {
		errorDetail = new Error(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Error>(errorDetail, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RateFoundExistException.class)
	public ResponseEntity<Error> rateFoundExistException(RateFoundExistException ex, WebRequest request) {
		errorDetail = new Error(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Error>(errorDetail, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RateNotFoundException.class)
	public ResponseEntity<Error> rateNotFoundExistException(RateNotFoundException ex, WebRequest request) {
		errorDetail = new Error(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Error>(errorDetail, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CarNotAsignRateException.class)
	public ResponseEntity<Error> carNotAsignRateException(CarNotAsignRateException ex, WebRequest request) {
		errorDetail = new Error(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Error>(errorDetail, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DateFormatNoValidException.class)
	public ResponseEntity<Error> dateFormatNoValidException(DateFormatNoValidException ex, WebRequest request) {
		errorDetail = new Error(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Error>(errorDetail, HttpStatus.NOT_FOUND);
	}
}