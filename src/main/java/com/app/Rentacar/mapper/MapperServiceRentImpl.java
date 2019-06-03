package com.app.Rentacar.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.app.Rentacar.dto.CarDTO;
import com.app.Rentacar.dto.RentDTO;
import com.app.Rentacar.dto.UserDTO;
import com.app.Rentacar.exceptions.DateFormatNoValidException;
import com.app.Rentacar.model.Car;
import com.app.Rentacar.model.Rent;
import com.app.Rentacar.model.User;
import com.app.Rentacar.tools.tools;

@Component
public class MapperServiceRentImpl implements MapperService<RentDTO, Rent> {

	@Autowired private MapperService<CarDTO, Car> mapperCar;
	@Autowired private MapperService<UserDTO, User> mapperUser;
	
	@Override
	public RentDTO mapToDto(Rent entity) {	
		final RentDTO rentDTO = new RentDTO();
		if (Optional.ofNullable(entity).isPresent()) {
			rentDTO.setId(entity.getId());
			rentDTO.setCar(mapperCar.mapToDto(entity.getCar()));
			rentDTO.setClient(mapperUser.mapToDto(entity.getUser()));			
			rentDTO.setStartDate(entity.getStartRent().toString());
			rentDTO.setEndDate(entity.getEndRent().toString());
			rentDTO.setPrice(entity.getPrice());
		}
		return rentDTO;
	}

	@Override
	public Rent mapToEntity(RentDTO dto) throws DateFormatNoValidException {
		final Rent rent = new Rent();		
		if (Optional.ofNullable(dto).isPresent()) {
			rent.setCar(mapperCar.mapToEntity(dto.getCar()));
			rent.setUser(mapperUser.mapToEntity(dto.getClient()));
			rent.setStartRent(tools.convertStringToLocalDate(dto.getStartDate()));
			rent.setEndRent(tools.convertStringToLocalDate(dto.getEndDate()));
			rent.setPrice(dto.getPrice());
		}
		return rent;
	}

	@Override
	public List<RentDTO> mapListToDto(List<Rent> listUser) {
		final List<RentDTO> listRentDTO = new ArrayList<>();
		listUser.forEach((b)-> {
			final RentDTO rentDTO = mapToDto(b);
			listRentDTO.add(rentDTO);		
		});
		return listRentDTO;
	}

	@Override
	public Page<RentDTO> mapPageToDto(Page<Rent> pageEntity) {
		return pageEntity.map(b-> mapToDto(b));		
	}
}