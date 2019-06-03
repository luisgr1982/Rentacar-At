package com.app.Rentacar.tools;


public class Constants {
	
	public static final String URI_USER_CONTROLLER= "/user";
	public static final String URI_CAR_CONTROLLER= "/car";
	public static final String URI_RENT_CONTROLLER="/rent";	
	public static final String URI_RATE_CONTROLLER="/rate";
	public static final String URI_USER_RENT_CONTROLLER = "/rent";
	public static final String URI_CAR_RENT_CONTROLLER = "/rent";
	public static final String URI_CAR_RATE_CONTROLLER = "/add";
	public static final String URI_CAR_PROFITABLE_CONTROLLER = "/findcar";
	
	public static final String EX_BAD_REQUEST="La URL introducida en su navegador no es correcta.";
	public static final String DELETE_USER_MESSAGE="Usuario eliminado correctamente";
	public static final String DELETE_CAR_MESSAGE ="Coche eliminado correctamente";
	public static final String DELETE_RENT_MESSAGE="Alquiler eliminado correctamente";
	
	public static final String CAR_NOT_FOUND_MESSAGE="Coche no encontrado en base de datos.";
	public static final String USER_NOT_FOUND_MESSAGE="Usuario no encontrado en base de datos.";
	public static final String RENT_NOT_FOUND_MESSAGE="Alquiler no encontrado en base de datos.";
	public static final String USER_FOUND_EXIST_MESSAGE="El Usuario introducido ya se encuentra dado de alta";
	public static final String CAR_FOUND_EXIST_MESSAGE="EL coche introducido ya se encuentra dado de alta";
	public static final String URL_NOT_FOUND_MESSAGE ="La URL introducida no es valida.";
	public static final String RATE_NOT_FOUND_MESSAGE = "La tarifa introducida no se encuentra dada de alta.";
	public static final String RATE_FOUND_EXIST_MESSAGE = "El id de tarifa introducido ya se encuentra dado de alta.";
	public static final String CAR_NOT_ASIGN_RATE_MESSAGE="El coche no tiene asignado ninguna tarifa.";
	public static final String DATE_FORMAT_NO_VALID_MESSAGE="La fecha no tiene un formato valido DD/MM/YYYY";
	
	public static final String DNI_NO_VALID="El DNI introducido no es valido";
	public static final String PATTERN_DATE = "[yyyy-MM-dd][MM-dd-yyyy][dd-MM-yyyy][yyyy/MM/dd][yyyy/dd/MM][dd/MM/yyyy][yyyyMMdd][ddMMyyyy][d/MM/yyyy]";
	public static final String PATTERN_DNI = "[0-9]{8,8}[A-Za-z]";
	public static final String LETTERS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";
}
