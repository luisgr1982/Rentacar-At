package com.app.Rentacar.mapper;


import java.util.List;

import org.springframework.data.domain.Page;

import com.app.Rentacar.exceptions.DateFormatNoValidException;

/**
 * Interfaz para mappear de DTO a Entity y viceversa.
 */
public interface MapperService <T, S>{
	/**
	 * Metodo que recibe una entidad y la mapea a dto
	 * 
	 * @param entity
	 * @return DTO
	 */
	T mapToDto (S entity);
	/**
	 * 
	 * Metodo que recibe un DTO y la mapea a Entidad
	 * @param dto
	 * @return Entity
	 * @throws DateFormatNoValidException 
	 */
	S mapToEntity (T dto) throws DateFormatNoValidException;
	/**
	 * 
	 * Metodo que recibe una lista de entidades User y lo mapea a lista de objeto tipo UserDTO.
	 * 
	 * @param listUser Lista con objetos User
	 * @return lista con objetos UserDTO
	 */
	List<T> mapListToDto(List<S> listUser);
	/**
	 * 
	 * Metodo que recibe un objeto Page de entidades y o mapea a DTO
	 * 
	 * @param pageEntity
	 * @return Page de DTO
	 * 
	 */
	Page<T> mapPageToDto(Page<S> pageEntity);
}
