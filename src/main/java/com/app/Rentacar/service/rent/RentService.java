package com.app.Rentacar.service.rent;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.Rentacar.dto.RentDTO;
import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.exceptions.CarNotAsignRateException;
import com.app.Rentacar.exceptions.CarNotFoundException;
import com.app.Rentacar.exceptions.DateFormatNoValidException;
import com.app.Rentacar.exceptions.UserNotFoundException;
import com.app.Rentacar.model.Rent;

public interface RentService {
	/**
	 * Metodo que crea un Alquiler, dado un idUser, idCar y los datos del alquiler en un objeto RentDTO
	 * 
	 * @param idUser Identificador de usuario
	 * @param idCar Identificador del coche
	 * @param rentDTO Objeto con los datos del alquiler
	 * @return Devuelve un objeto Rent guardado en BD
	 * @throws UserNotFoundException 
	 * @throws CarNotFoundException 
	 * @throws CarNotAsignRateException 
	 * @throws DateFormatNoValidException 
 	 *
	 */
	Rent createRentService(Integer idUser, Integer idCar, RentDTO rentDTO) throws UserNotFoundException, CarNotFoundException, CarNotAsignRateException, DateFormatNoValidException;
	/**
	 * 
	 * Metodo que elimina un Alquiler dado un identificador de Alquiler
	 * 
	 * @param idRent Identificador del alquiler
	 */
	void deleteRentService(Integer idRent);
	/**
	 * 
	 * Metodo que obtiene un alquiler
	 * 
	 * @param idUser Identificador del cliente
	 * @param idCar Identificador del coche
	 * @param idRent identificador del alquiler
	 * @param state Estado que se encuentra en BD
	 * 
	 * @return un objeto Rent con los datos del alquiler
	 * @throws UserNotFoundException 
	 * @throws CarNotFoundException 
	 */
	Rent getRentService(Integer idUser, Integer idCar, Integer idRent, StateEnum state) throws UserNotFoundException, CarNotFoundException;
	/**
	 * 
	 * Metodo que devuelve todas las rentas de un usuario y coche dado
	 * 
	 * @param idUser Identificador de usuario
	 * @param idCar Identificador de coche
	 * @param pageable
	 * @return Devuelve una lista paginada con los alquileres entre usuario y coche
	 */
	Page<Rent> getAllRentService(Integer idUser, Integer idCar, Pageable pageable);
	/**
	 * 
	 * Metodo que actualiza un alquiler ya dado de alta.
	 * 
	 * @param idRent 	Identificador de alquiler
	 * @param RentDTO	DTO recibido desde cliente 
	 * @param state		Estado en BD ACTIVO O DISABLED
	 * @return Devuelve la entidad guardada
	 * @throws DateFormatNoValidException 
	 */
	Rent updateRentService(Integer idRent, RentDTO rentDTO, StateEnum state) throws DateFormatNoValidException;
	/**
	 * 
	 * Metodo que mape una lista de Rent a RentDTO
	 * 
	 * @param rents
	 * @return
	 */
	List<RentDTO> getListRent(List<Rent> rents);
}
