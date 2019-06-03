package com.app.Rentacar.service.rent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.Rentacar.dao.RentRepository;
import com.app.Rentacar.dto.RentDTO;
import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.exceptions.CarNotAsignRateException;
import com.app.Rentacar.exceptions.CarNotFoundException;
import com.app.Rentacar.exceptions.DateFormatNoValidException;
import com.app.Rentacar.exceptions.RentNotFoundException;
import com.app.Rentacar.exceptions.UserNotFoundException;
import com.app.Rentacar.mapper.MapperService;
import com.app.Rentacar.model.Rent;
import com.app.Rentacar.service.car.CarService;
import com.app.Rentacar.service.rate.RateService;
import com.app.Rentacar.service.user.UserService;
import com.app.Rentacar.tools.tools;

@Service
public class RentServiceImpl implements RentService {
	@Autowired private UserService userService;
	@Autowired private CarService carService;
	@Autowired private RateService rateService;
	@Autowired private RentRepository rentRepository;
	@Autowired private MapperService<RentDTO, Rent> mapper;

	@Override
	public Rent createRentService(Integer idUser, Integer idCar, RentDTO rentDTO) throws UserNotFoundException, CarNotFoundException, CarNotAsignRateException, DateFormatNoValidException {
		final Optional<Rent> rent = Optional.ofNullable(mapper.mapToEntity(rentDTO));
		if (rent.isPresent()) {
			rent.get().setUser(userService.getUserService(idUser, StateEnum.ACTIVE));
			rent.get().setCar(carService.getCarService(idCar, StateEnum.ACTIVE));
			rent.get().setStartRent(tools.convertStringToLocalDate(rentDTO.getStartDate()));
			rent.get().setEndRent(tools.convertStringToLocalDate(rentDTO.getEndDate()));
			if (rent.get().getCar().getRates().isEmpty())
				throw new CarNotAsignRateException();			
			rent.get().setPriceRate(
					Optional.ofNullable(rateService.getActiveRateCarService
							(rent.get().getStartRent(), rent.get().getCar().getRates())).get());
			
			rent.get().setState(StateEnum.ACTIVE);
			rent.get().setDateCreatedRent(new Date());
			rentRepository.save(rent.get());			
		}
		return rent.get();
	}

	@Override
	public void deleteRentService(Integer idRent) {		
		final Rent rent = Optional.ofNullable(rentRepository.findByIdAndState(idRent, StateEnum.ACTIVE))
				.orElseThrow(RentNotFoundException::new);
		rent.setState(StateEnum.DISABLE);
		rentRepository.save(rent);
	}

	@Override
	public Rent getRentService(Integer idUser, Integer idCar, Integer idRent, StateEnum state) throws UserNotFoundException, CarNotFoundException {
		if (!Optional.ofNullable(userService.getUserService(idUser, StateEnum.ACTIVE)).isPresent()) throw new UserNotFoundException();
		if (!Optional.ofNullable(carService.getCarService(idCar, StateEnum.ACTIVE)).isPresent()) throw new CarNotFoundException();
		return Optional.ofNullable(rentRepository.findByIdAndState(idRent, state))
				.orElseThrow(RentNotFoundException::new);
	}

	@Override
	public Page<Rent> getAllRentService(Integer idUser, Integer idCar, Pageable pageable) {
		return rentRepository.findByUserIdAndCarIdAndState(idUser, idCar, StateEnum.ACTIVE, pageable);
	}

	@Override
	public Rent updateRentService(Integer idRent, RentDTO rentDTO, StateEnum state) throws DateFormatNoValidException {
		final Rent rent = Optional.ofNullable(rentRepository.findByIdAndState(idRent, StateEnum.ACTIVE))
				.orElseThrow(RentNotFoundException::new);
		rent.setStartRent(tools.convertStringToLocalDate(rentDTO.getStartDate()));
		rent.setEndRent(tools.convertStringToLocalDate(rentDTO.getEndDate()));
		rent.setPrice(rentDTO.getPrice());
		return rentRepository.save(rent);
	}
	@Override
	public List<RentDTO> getListRent(List<Rent> rents) {
		final List<RentDTO> listRentDTO = new ArrayList<>();
		rents.forEach(b->{
			if (b.getState().equals(StateEnum.ACTIVE))
				listRentDTO.add(mapper.mapToDto(b));				
		});
		return listRentDTO;
	}
}