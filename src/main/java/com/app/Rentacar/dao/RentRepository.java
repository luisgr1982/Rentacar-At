package com.app.Rentacar.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.model.Rent;

public interface RentRepository extends JpaRepository<Rent, Integer>{	
	/**
	 * 
	 * Metodo que busca un aquiler por un identificador y estado en BD
	 * 
	 * @param idRent Identificador del alquiler
	 * @param state ACTIVE O DISABLED
	 * @return Devuelve un objeto Rent con los datos guardados en BD.
	 * 
	 */
	public Rent findByIdAndState(Integer idRent, StateEnum state);
	/**
	 * 
	 * Metodo que buscar un alquiler por id user, id car, y estado en bd.
	 * 
	 * @param userRent Identificador del usuario
	 * @param carRent Identificador del coche
	 * @param state Estado en base de datos (ACTIVE O DISABLE)
	 * @param pageable Parametros de paginación
	 * 
	 * @return Page Devuelve una lista paginada de todos los alquileres entre coche y usuario dado.
	 */
	public Page<Rent> findByUserIdAndCarIdAndState(Integer userRent, Integer carRent, StateEnum state, Pageable pageable);
}
