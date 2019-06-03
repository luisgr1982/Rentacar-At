package com.app.Rentacar.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.app.Rentacar.dto.RateDTO;
import com.app.Rentacar.exceptions.DateFormatNoValidException;
import com.app.Rentacar.model.Rate;
import com.app.Rentacar.tools.tools;

@Component
public class MapperServiceRateImpl implements MapperService<RateDTO, Rate> {

	@Override
	public RateDTO mapToDto(Rate rate) {
		final RateDTO rateDTO = new RateDTO();
		if (Optional.ofNullable(rate).isPresent()) {
			rateDTO.setId(rate.getId());
			rateDTO.setStartDate(rate.getStartRate().toString());
			rateDTO.setPrice(rate.getPriceRate());
			if (Optional.ofNullable(rateDTO.getEndDate()).isPresent())
				rateDTO.setEndDate(rate.getEndRate().toString());			
		}
		return rateDTO;
	}

	@Override
	public Rate mapToEntity(RateDTO rateDTO) throws DateFormatNoValidException {
		final Rate rate = new Rate();
		if (Optional.ofNullable(rateDTO).isPresent()) {
			rate.setPriceRate(rateDTO.getPrice());
			rate.setStartRate(tools.convertStringToLocalDate(rateDTO.getStartDate()));
			if (Optional.ofNullable(rateDTO.getEndDate()).isPresent() && !rateDTO.getEndDate().equals(""))
				rate.setEndRate(tools.convertStringToLocalDate(rateDTO.getEndDate()));			
		}
		return rate;
	}

	@Override
	public List<RateDTO> mapListToDto(List<Rate> listRate) {
		final List<RateDTO> listRateDTO = new ArrayList<>();
		listRate.forEach((b) -> {
			final RateDTO rateDTO = mapToDto(b);
			listRateDTO.add(rateDTO);
		});
		return listRateDTO;
	}

	@Override
	public Page<RateDTO> mapPageToDto(Page<Rate> pageEntity) {
		return pageEntity.map(b -> mapToDto(b));
	}
}