package com.app.Rentacar.dao;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
	/**
	 * 
	 * Metodo que saca una lista de coches dependiendo de si el estado es ACTIVE o
	 * DISABLED
	 * 
	 * @param state    Estado del coche en BD (ACTIVE O DISABLED)
	 * @param pageable objeto pageable con los datos para la paginacion
	 * 
	 * @return Page<Car> Listado con los coches activos
	 * 
	 */
	public Page<Car> findByState(StateEnum state, Pageable pageable);

	/**
	 * 
	 * Metodo que busca un coche en BD por id y por estado ACTIVE o DISABLED
	 * 
	 * @param id    Identificador del coche
	 * @param State Estado del coche en BD (ACTIVE o DISABLED)
	 * @return Devuelve un objeto Car con todas sus propiedades
	 * 
	 */
	public Car findByIdAndState(Integer id, StateEnum state);

	/**
	 * Query, dado un rango de fechas, obtener el id del coche mas rentable.
	 * 
	 */
	@Query(value = "SELECT id FROM (select c.id, SUM(r.price_rate * ABS(DATEDIFF(IF(start_rent < :start, :start, start_rent), IF(end_rent > :end, :end, end_rent)))) as suma from car c join rent r on r.car_id = c.id where (r.start_rent BETWEEN :start AND :end) OR (r.end_rent BETWEEN :start AND :end) OR (r.start_rent <= :start AND r.end_rent >= :end) group by c.id order by suma desc LIMIT 1) t", nativeQuery = true)
	public Integer findCarMostProfitableARangeOfDates(@Param("start") LocalDate startDate,
			@Param("end") LocalDate endDate);
}