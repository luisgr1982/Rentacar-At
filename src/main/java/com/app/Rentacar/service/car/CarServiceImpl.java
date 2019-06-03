package com.app.Rentacar.service.car;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.Rentacar.dao.CarRepository;
import com.app.Rentacar.dto.CarDTO;
import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.exceptions.CarFoundExistException;
import com.app.Rentacar.exceptions.CarNotFoundException;
import com.app.Rentacar.exceptions.DateFormatNoValidException;
import com.app.Rentacar.exceptions.RateNotFoundException;
import com.app.Rentacar.mapper.MapperService;
import com.app.Rentacar.model.Car;
import com.app.Rentacar.service.rate.RateService;
import com.app.Rentacar.service.rent.RentService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired private MapperService<CarDTO, Car> mapper;
	@Autowired private CarRepository carRepository;
	@Autowired private RentService rentService;
	@Autowired private RateService rateService;
	
	@Override
	public Car createCarService(CarDTO carDTO) throws CarFoundExistException, DateFormatNoValidException {
		final Optional<Car> car = Optional.ofNullable(mapper.mapToEntity(carDTO));
		if (Optional.ofNullable((car.get().getId())).isPresent() && 
				Optional.ofNullable(carRepository.findById(carDTO.getId())).isPresent())
			throw new CarFoundExistException();
		
		car.get().setState(StateEnum.ACTIVE);
		car.get().setDateCreatedCar(new Date());
		return carRepository.save(car.get());
	}

	@Override
	public Car getCarService(Integer idCar, StateEnum state) throws CarNotFoundException {
		return Optional.ofNullable(carRepository.findByIdAndState(idCar, state))
				.orElseThrow(CarNotFoundException::new);
	}

	@Override
	public Page<Car> getListCarService(StateEnum state, Pageable pageable) throws CarNotFoundException {
		return Optional.ofNullable(carRepository.findByState(state, pageable)).orElseThrow(CarNotFoundException::new);
	}

	@Override
	public void deleteCarService(Integer idCar) throws CarNotFoundException {
		final Car car = this.getCarService(idCar, StateEnum.ACTIVE);
		car.setState(StateEnum.DISABLE);
		carRepository.save(car);
	}

	@Override
	public Car updateCarService(Integer idCar, CarDTO carUpdate) throws CarNotFoundException {
		final Car car = this.getCarService(idCar, StateEnum.ACTIVE);
		car.setCarPlate(carUpdate.getCarPlate());
		car.setRegistrationYear(carUpdate.getRegistrationYear());
		return carRepository.save(car);
	}
	@Override
	public List<?> getListRentCarService(Integer idCar, Pageable pageable) throws CarNotFoundException {
		final Car car = this.getCarService(idCar, StateEnum.ACTIVE);
		return rentService.getListRent(car.getRents());
	}
	
	@Override
	public List<?> getListRateCarService(Integer idCar, Pageable pageable) throws CarNotFoundException {
		final Car car = this.getCarService(idCar, StateEnum.ACTIVE);
		return rateService.getListRate(car.getRates());
	}
	@Override
	public Car updateCarRateService(Integer idCar, Integer idRate) throws RateNotFoundException, CarNotFoundException {
		final Car car = this.getCarService(idCar, StateEnum.ACTIVE);
		car.getRates().add(rateService.getRateService(idRate));
		return carRepository.save(car);
	}

	@Override
	public Car getCarMostProfitableARangeOfDates(LocalDate startDate, LocalDate endDate) throws CarNotFoundException {
		return this.getCarService(carRepository.findCarMostProfitableARangeOfDates(endDate, startDate), StateEnum.ACTIVE);
	}
}