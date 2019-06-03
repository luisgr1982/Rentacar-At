package com.app.Rentacar.service.car;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.Rentacar.dto.CarDTO;
import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.exceptions.CarFoundExistException;
import com.app.Rentacar.exceptions.CarNotFoundException;
import com.app.Rentacar.exceptions.DateFormatNoValidException;
import com.app.Rentacar.exceptions.RateNotFoundException;
import com.app.Rentacar.exceptions.UserFoundExistException;
import com.app.Rentacar.model.Car;

public interface CarService {
	/**
	 * 
	 * Metodo que recibe un object CarDTO y guarda en BD
	 * 
	 * @param carDTO Datos del coche a crear en BD
	 * @return Devuelve un objeto Car guardado en BD.
	 * @throws DateFormatNoValidException 
	 * @throws CarNotFoundException
	 * @throws UserNotFoundException
	 * 
	 */
	Car createCarService(CarDTO carDTO) throws CarFoundExistException, UserFoundExistException, DateFormatNoValidException;
	/**
	 * 
	 * Metodo que recibe un identificador de coche y un estado, busca en BD y devuelve un objeto CarDTO
	 * 
	 * @param idCar Identificador de coche
	 * @param state  Estado en base de datos (ACTIVE o DISABLED)
	 * @return Car Devuelve objeto Car obtenido de base de datos según los parametros recibidos.
	 * @throws CarNotFoundException
	 * 
	 */
	Car getCarService(Integer idCar, StateEnum state) throws CarNotFoundException;
	/**
	 * 
	 * Metodo que devuelve una lista paginada con los coches según estado en base de datos
	 * 
	 * @param state Estado según se encuentra en BD (ACITVE O DISABLED)
	 * @param pageable Objeto pageable con los datos para la paginacion
	 * 
	 * @return Devuelve un objeto Page de Car según el estado enviado como parametro.
	 * @throws CarNotFoundException 
	 *  
	 */
	Page<Car> getListCarService(StateEnum state, Pageable pageable) throws CarNotFoundException;
	/**
	 * 
	 * Metodo que según el identificador de coche, cambia de estado en la base de datos del coche dado de ACTIVE o DISABLED.
	 * 
	 * @param idUser Identificador de coche
	 * @throws CarNotFoundException 
	 * 
	 */
	void deleteCarService(Integer idCar)throws CarNotFoundException;
	/**
	 * 
	 * Metodo que dado un objeto CarDTO enviado por parametro, lo guarda en BD y devuelve la respuesta.
	 * 
	 * @param idCar  		Identificador de coche
	 * @param carDTO 		Recibimos un objeto CarDTO
	 * 
	 * @return Devuelve un objeto Car con los datos modificados en base de datos.
	 * @throws CarNotFoundException 
	 * 
	 */
	Car updateCarService(Integer idCar, CarDTO carDTO) throws CarNotFoundException;
	/**
	 * 
	 * Metodo que devuelve una lista con los alquileres de un coche dado.
	 * 
	 * @param idCar Identificador del coche
	 * @param pageable Datos de la paginación.
	 * @return Lista de alquileres del coche
	 * @throws CarNotFoundException 
	 */
	List<?> getListRentCarService(Integer idCar, Pageable pageable) throws CarNotFoundException;
	/**
	 * 
	 * Metodo que guarda en la lista de tarifas de un coche una nueva tarifa.
	 * 
	 * @param idCar Identificador del coche
	 * @param idRate Identificador de la tarifa
	 * @throws RateNotFoundException 
	 * @throws CarNotFoundException 
	 */
	Car updateCarRateService(Integer idCar, Integer idRate) throws RateNotFoundException, CarNotFoundException;
	/**
	 * 
	 * Metodo que devuelve una lista de tarifas de un coche dado.
	 * 
	 * @param idCar Identificador del coche
	 * @param pageable Parametros de la paginación.
	 * @return Lista con las tarifas del coche dado.
	 * @throws CarNotFoundException 
	 * 
	 */
	List<?> getListRateCarService(Integer idCar, Pageable pageable) throws CarNotFoundException;
	/**
	 * Metodo que devuelve el coche mas rentable dado un rango de fechas.
	 * 
	 * @param startDate Inicio del rango
	 * @param endDate fin del rango
	 * @return Devuelve la entidad del coche
	 * @throws CarNotFoundException
	 */
	Car getCarMostProfitableARangeOfDates(LocalDate startDate, LocalDate endDate) throws CarNotFoundException;
}