package com.app.Rentacar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Rentacar.dto.CarDTO;
import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.exceptions.CarFoundExistException;
import com.app.Rentacar.exceptions.CarNotFoundException;
import com.app.Rentacar.exceptions.DateFormatNoValidException;
import com.app.Rentacar.exceptions.UserFoundExistException;
import com.app.Rentacar.mapper.MapperService;
import com.app.Rentacar.model.Car;
import com.app.Rentacar.service.car.CarService;
import com.app.Rentacar.tools.Constants;
import com.app.Rentacar.tools.tools;

@RestController
@RequestMapping(Constants.URI_CAR_CONTROLLER)
public class CarController {
	@Autowired private CarService carService;
	@Autowired private MapperService<CarDTO, Car> mapper;

	@GetMapping
	public ResponseEntity<Page<CarDTO>> getAllCar(@PageableDefault(page = 0, value = 10) Pageable pageable)
			throws CarNotFoundException {
		return new ResponseEntity<Page<CarDTO>>(
				mapper.mapPageToDto(carService.getListCarService(StateEnum.ACTIVE, pageable)), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarDTO> getCar(@PathVariable("id") Integer idCar) throws CarNotFoundException {
		return new ResponseEntity<CarDTO>(mapper.mapToDto(carService.getCarService(idCar, StateEnum.ACTIVE)),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CarDTO> createCar(@Valid @RequestBody CarDTO carDTO)
			throws CarFoundExistException, UserFoundExistException, DateFormatNoValidException {
		return new ResponseEntity<CarDTO>(mapper.mapToDto(carService.createCarService(carDTO)), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCar(@PathVariable("id") Integer idCar) throws CarNotFoundException {
		carService.deleteCarService(idCar);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> putCar(@PathVariable("id") Integer idCar, @Valid @RequestBody CarDTO carDTO)
			throws CarNotFoundException {
		return new ResponseEntity<CarDTO>(mapper.mapToDto(carService.updateCarService(idCar, carDTO)), HttpStatus.OK);
	}

	@GetMapping("/{id}" + Constants.URI_CAR_RENT_CONTROLLER)
	public ResponseEntity<?> getListRentCar(@PageableDefault(page = 0, value = 10) Pageable pageable,
			@PathVariable("id") Integer idCar) throws CarNotFoundException {
		List<?> listCarRent = carService.getListRentCarService(idCar, pageable);
		return new ResponseEntity<>(new PageImpl<>(listCarRent, pageable, listCarRent.size()), HttpStatus.OK);
	}

	@GetMapping("/{id}" + Constants.URI_RATE_CONTROLLER)
	public ResponseEntity<?> getListRateCar(@PageableDefault(page = 0, value = 10) Pageable pageable,
			@PathVariable("id") Integer idCar) throws CarNotFoundException {
		List<?> listCarRate = carService.getListRateCarService(idCar, pageable);
		return new ResponseEntity<>(new PageImpl<>(listCarRate, pageable, listCarRate.size()), HttpStatus.OK);
	}

	@GetMapping(Constants.URI_CAR_PROFITABLE_CONTROLLER)
	public ResponseEntity<?> getCarMostProfitable(@RequestParam(name = "startDate") String startDate,
			@RequestParam(name = "endDate") String endDate) throws CarNotFoundException, DateFormatNoValidException {
		return new ResponseEntity<CarDTO>(mapper.mapToDto(carService.getCarMostProfitableARangeOfDates(
				tools.convertStringToLocalDate(startDate), tools.convertStringToLocalDate(endDate))), HttpStatus.OK);
	}
}