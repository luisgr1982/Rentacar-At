package com.app.Rentacar.service.rate;


import java.time.LocalDate;
import java.util.List;

import com.app.Rentacar.dto.RateDTO;
import com.app.Rentacar.exceptions.CarNotAsignRateException;
import com.app.Rentacar.exceptions.CarNotFoundException;
import com.app.Rentacar.exceptions.DateFormatNoValidException;
import com.app.Rentacar.exceptions.RateFoundExistException;
import com.app.Rentacar.exceptions.RateNotFoundException;
import com.app.Rentacar.model.Rate;

public interface RateService {
	/**
	 * 
	 * Metodo que crea una nueva tarifa
	 * 
	 * @param RateDTO
	 * @return Devuelve la entidad guardada en base de datos.
	 * @throws RateFoundExistException 
	 * @throws DateFormatNoValidException 
	 * 
	 */
	Rate createRateService(RateDTO rateDTO) throws RateFoundExistException, DateFormatNoValidException;
	/**
	 * 
	 * Metodo que asigna una tarifa a un coche dado
	 * 
	 * @param 	idRate 	Identificador de tarifa
	 * @param 	idCar 	Identificador de coche
	 * @throws 	RateFoundExistException 
	 * @throws 	RateNotFoundException 
	 * @throws 	CarNotFoundException 
	 * 
	 */
	void asignRateToCarService(Integer idRate, Integer idCar) throws RateNotFoundException, CarNotFoundException;
	/**
	 * 
	 * Metodo que obtiene una tarifa según su identificador
	 * 
	 * @param 	idRate 	Identificador de la tarifa
	 * @return 	Rate 	Devuelve la tarifa guardada en BD.
	 * @throws RateNotFoundException
	 */
	Rate getRateService(Integer idRate) throws RateNotFoundException;
	/**
	 * 
	 * Metodo que obtiene el precio activo de la tarifa de un coche.
	 * 
	 * @param dateStart Fecha inicio del alquiler
	 * @param listRate	Lista de tarifas del coche.
	 * @return	Devuelve el precio de la tarifa.
	 * @throws CarNotAsignRateException 
	 */
	double getActiveRateCarService(LocalDate dateStart, List<Rate> listRate) throws CarNotAsignRateException;
	/**
	 * 
	 * Metodo que mapea una lista de tarifas de Entidad a DTO
	 * @param rate		Tarifa(ENtidad)
	 * @return RateDTO	Tarifa(DTO)
	 */
	List<RateDTO> getListRate(List<Rate> rate);
}