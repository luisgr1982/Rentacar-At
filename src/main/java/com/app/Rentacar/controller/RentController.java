package com.app.Rentacar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RestController;

import com.app.Rentacar.dto.RentDTO;
import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.exceptions.CarNotAsignRateException;
import com.app.Rentacar.exceptions.CarNotFoundException;
import com.app.Rentacar.exceptions.DateFormatNoValidException;
import com.app.Rentacar.exceptions.RentNotFoundException;
import com.app.Rentacar.exceptions.UserNotFoundException;
import com.app.Rentacar.mapper.MapperService;
import com.app.Rentacar.model.Rent;
import com.app.Rentacar.service.rent.RentService;
import com.app.Rentacar.tools.Constants;

@RestController
@RequestMapping(Constants.URI_USER_CONTROLLER + "/{idUser}" + Constants.URI_CAR_CONTROLLER + "/{idCar}"
		+ Constants.URI_RENT_CONTROLLER)
public class RentController {
	
	@Autowired private RentService rentService;
	@Autowired private MapperService<RentDTO, Rent> mapper;

	@GetMapping
	public ResponseEntity<Page<RentDTO>> getAllRent(@PathVariable("idUser") Integer idUser,
			@PathVariable("idCar") Integer idCar, @PageableDefault(page = 0, value = 10) Pageable pageable) {
		return new ResponseEntity<>(mapper.mapPageToDto(rentService.getAllRentService(idUser, idCar, pageable)),
				HttpStatus.OK);
	}

	@GetMapping("/{idRent}")
	public ResponseEntity<RentDTO> getRent(@PathVariable("idUser") Integer idUser, @PathVariable("idCar") Integer idCar,
			@PathVariable("idRent") Integer idRent) throws RentNotFoundException, UserNotFoundException, CarNotFoundException {
		return new ResponseEntity<RentDTO>(
				mapper.mapToDto(rentService.getRentService(idUser, idCar, idRent, StateEnum.ACTIVE)), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<RentDTO> createRent(@PathVariable("idUser") Integer idUser,
			@PathVariable("idCar") Integer idCar, @Valid @RequestBody RentDTO rentDTO) throws UserNotFoundException, CarNotFoundException, CarNotAsignRateException, DateFormatNoValidException{
		return new ResponseEntity<RentDTO>(mapper.mapToDto(rentService.createRentService(idUser, idCar, rentDTO)),
				HttpStatus.OK);
	}

	@DeleteMapping("/{idRent}")
	public ResponseEntity<?> deleteRent(@PathVariable("idRent") Integer idRent) throws RentNotFoundException {
		rentService.deleteRentService(idRent);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{idRent}")
	public ResponseEntity<RentDTO> putRent(@PathVariable("idRent") Integer idRent, @Valid @RequestBody RentDTO rentDTO)
			throws RentNotFoundException, DateFormatNoValidException {
		return new ResponseEntity<>(mapper.mapToDto(rentService.updateRentService(idRent, rentDTO, StateEnum.ACTIVE)), HttpStatus.OK);
	}	
}