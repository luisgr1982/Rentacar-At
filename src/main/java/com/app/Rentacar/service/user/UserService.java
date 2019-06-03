package com.app.Rentacar.service.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.Rentacar.dto.UserDTO;
import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.exceptions.DateFormatNoValidException;
import com.app.Rentacar.exceptions.UserFoundExistException;
import com.app.Rentacar.exceptions.UserNotFoundException;
import com.app.Rentacar.model.User;

public interface UserService {
	/**
	 * 
	 * Metodo que recibe un object UserDTO y guarda en base de datos
	 * 
	 * @param user
	 * @return Devuelve un objeto User guardado en base de datos.
	 * @throws UserFoundExistException 
	 * @throws DateFormatNoValidException 
	 * 
	 */
	User createUserService(UserDTO userDTO) throws UserFoundExistException, DateFormatNoValidException;
	/**
	 * 
	 * Metodo que recibe un identificador de usuario y un estado, busca en BD y devuelve un objeto userDTO
	 * 
	 * @param idUser Identificador de usuario
	 * @param state  Estado en base de datos (ACTIVE o DISABLED)
	 * @return userDTO Devuelve objeto User guardado en base de datos.
	 * 
	 */
	User getUserService(Integer idUser, StateEnum state) throws UserNotFoundException;
	/**
	 * 
	 * Metodo que devuelve un Page con los usuarios según estado
	 * 
	 * @param state Estado según se encuentra en BD (ACITVE O DISABLED)
	 * @param pageable Objeto Pageable para paginar la lista
	 * 
	 * @return Devuelve un objeto Page con objetos User según el state enviado como parametro
	 *  
	 */
	Page<User> getListUserService(StateEnum state, Pageable pageable);
	/**
	 * 
	 * Metodo que según el identificador de usuario, cambia de estado en la base de datos e usuario. De ACTIVE A DISABLED.
	 * 
	 * @param idUser 		Identificado de usuario
	 * 
	 */
	void deleteUserService(Integer idUser)throws UserNotFoundException;
	/**
	 * 
	 * Metodo dado un objeto UserDTO recibido por parametro, lo guarda en BD y devuelve la respuesta.
	 * 
	 * @param idUser  		Identificador de usuario
	 * @param userDTO 		Recibimos un objeto userDTO
	 * 
	 * @return 				Devuelve un objeto User con los datos modificados en BD.
	 * 
	 */
	User updateUserService(Integer idUser, UserDTO userDTO) throws UserNotFoundException;
	
	/**
	 * Metodo que devuelve una lista con los alquileres de un usuario dado
	 * 
	 * @param idUser 	Identificador del usuario
	 * @param pageable	Datos dela paginación
	 * @return			Devuelve la lista paginada
	 * @throws UserNotFoundException 
	 */
	List<?> getListRentUserService(Integer idUser, Pageable pageable) throws UserNotFoundException;
}
