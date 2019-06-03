package com.app.Rentacar.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	/**
	 * 
	 * Metodo que saca una lista de usuarios dependiendo de si el estado es ACTIVE o DISABLED
	 * 
	 * @param state Estado del usuario en BD (ACTIVE O DISABLED)
	 * @return Devuelve un objeto Page con los usuarios activos
	 * 
	 */
	public Page<User> findByState(StateEnum state, Pageable pageable);
	/**
	 * 
	 * Metodo que busca un usuario en BD por id y por estado ACTIVE o DISABLED
	 * 
	 * @param id Identificador de usuario
	 * @param State  Estado del usuario en BD (ACTIVE o DISABLED)
	 * @return Devuelve un objeto User con todas sus propiedades
	 * 
	 */
	public User findByIdAndState(Integer id, StateEnum state);
	/**
	 * 
	 * Metodo que devuelve todos los usuario según el estado ACTIVE O DISABLE en formato paginado.
	 * 
	 * @param Pageable Objeto Pageable con los parametros de la paginación.
	 * @param StateEnum ACTIVE O DISABLED
	 * @return Devuelve un objeto Page con los usuarios.
	 */
	public Page<User> findAllByState(StateEnum state, Pageable pageable);
}
