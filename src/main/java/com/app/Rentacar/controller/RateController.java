package com.app.Rentacar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Rentacar.dto.RateDTO;
import com.app.Rentacar.exceptions.CarNotFoundException;
import com.app.Rentacar.exceptions.DateFormatNoValidException;
import com.app.Rentacar.exceptions.RateFoundExistException;
import com.app.Rentacar.exceptions.RateNotFoundException;
import com.app.Rentacar.mapper.MapperService;
import com.app.Rentacar.model.Rate;
import com.app.Rentacar.service.rate.RateService;
import com.app.Rentacar.tools.Constants;

@RestController
@RequestMapping(Constants.URI_RATE_CONTROLLER)
public class RateController {
	
	@Autowired private RateService rateService;
	@Autowired private MapperService<RateDTO, Rate> mapper;
	
	@PostMapping
	public ResponseEntity<RateDTO> createRate(@Valid @RequestBody RateDTO rateDTO) throws RateFoundExistException, DateFormatNoValidException {
		return new ResponseEntity<>(mapper.mapToDto(rateService.createRateService(rateDTO)), HttpStatus.OK);
	}
	
	@PutMapping("/{idRate}" + Constants.URI_CAR_CONTROLLER + "/{idCar}")
	public void asignRateToCar(@PathVariable("idRate") Integer idRate, @PathVariable("idCar") Integer idCar) throws RateFoundExistException, RateNotFoundException, CarNotFoundException {
		rateService.asignRateToCarService(idRate, idCar);
	}	
}
