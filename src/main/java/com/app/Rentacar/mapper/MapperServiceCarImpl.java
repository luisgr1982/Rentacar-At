package com.app.Rentacar.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.app.Rentacar.dto.CarDTO;
import com.app.Rentacar.model.Car;

@Component
public class MapperServiceCarImpl implements MapperService<CarDTO, Car> {

	@Override
	public CarDTO mapToDto(Car entity) {
		final CarDTO carDTO = new CarDTO();
		if (Optional.ofNullable(entity).isPresent()) {
			carDTO.setId(entity.getId());
			carDTO.setCarPlate(entity.getCarPlate());
			carDTO.setRegistrationYear(entity.getRegistrationYear());
		}
		return carDTO;
	}

	@Override
	public Car mapToEntity(CarDTO dto) {
		final Car car = new Car();
		if (Optional.ofNullable(dto).isPresent()) {
			car.setId(dto.getId());
			car.setCarPlate(dto.getCarPlate());
			car.setRegistrationYear(dto.getRegistrationYear());
		}
		return car;
	}

	@Override
	public List<CarDTO> mapListToDto(List<Car> listCar) {
		final List<CarDTO> listCarDTO = new ArrayList<>();
		listCar.forEach((b)-> {
			final CarDTO carDTO = mapToDto(b);
			listCarDTO.add(carDTO);		
		});
		return listCarDTO;		
	}

	@Override
	public Page<CarDTO> mapPageToDto(Page<Car> pageEntity) {
		return pageEntity.map(b-> mapToDto(b));
	}
}