package com.app.Rentacar.service.rate;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Rentacar.dao.RateRepository;
import com.app.Rentacar.dto.RateDTO;
import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.exceptions.CarNotAsignRateException;
import com.app.Rentacar.exceptions.CarNotFoundException;
import com.app.Rentacar.exceptions.DateFormatNoValidException;
import com.app.Rentacar.exceptions.RateFoundExistException;
import com.app.Rentacar.exceptions.RateNotFoundException;
import com.app.Rentacar.mapper.MapperService;
import com.app.Rentacar.model.Rate;
import com.app.Rentacar.service.car.CarService;

@Service
public class RateServiceImpl implements RateService {
	
	@Autowired private MapperService<RateDTO, Rate> mapper;
	@Autowired private RateRepository rateRepository;
	@Autowired private CarService carService;

	@Override
	public Rate createRateService(RateDTO rateDTO) throws RateFoundExistException, DateFormatNoValidException {
		final Optional<Rate> rate = Optional.ofNullable(mapper.mapToEntity(rateDTO));
		if (Optional.ofNullable((rate.get().getId())).isPresent() 
				&& Optional.ofNullable(rateRepository.findById(rateDTO.getId())).isPresent())
				throw new RateFoundExistException();
		
		rate.get().setDateCreatedRate(new Date());
		return rateRepository.save(rate.get());
	}
	
	@Override
	public Rate getRateService(Integer idRate) throws RateNotFoundException {
		return Optional.ofNullable(rateRepository.findById(idRate)).orElseThrow(RateNotFoundException::new).get();
	}
	
	@Override
	public void asignRateToCarService(Integer idRate, Integer idCar) throws RateNotFoundException, CarNotFoundException {
		Optional<Rate> rate = Optional.ofNullable(rateRepository.findById(idRate)).get();		
		if (rate.isPresent()) {
			rate.get().getCars().add(carService.getCarService(idCar, StateEnum.ACTIVE));
			rateRepository.save(rate.get());
			carService.updateCarRateService(idCar, idRate);
		}
		else
			throw new RateNotFoundException();
	}	
	
	@Override
	public double getActiveRateCarService(LocalDate dateStart, List<Rate> listRate) throws CarNotAsignRateException {		
		Rate rate = Optional.ofNullable(new Rate()).get();
		for (Rate iter : listRate) {
			if (iter.getStartRate().isBefore(dateStart) && 
					(!Optional.ofNullable(iter.getEndRate()).isPresent() || iter.getEndRate().isAfter(dateStart))) {
				if(!Optional.ofNullable(rate.getStartRate()).isPresent() || iter.getStartRate().isAfter(Optional.ofNullable(rate.getStartRate()).get())) {
					rate = iter;
				}
			}
		}
		return Optional.ofNullable(rate.getPriceRate()).orElseThrow(CarNotAsignRateException::new);
	}
	
	@Override
	public List<RateDTO> getListRate(List<Rate> rate) {
		return mapper.mapListToDto(rate);
	}
}