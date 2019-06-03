package com.app.Rentacar.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.app.Rentacar.dto.RateDTO;
import com.app.Rentacar.exceptions.DateFormatNoValidException;
import com.app.Rentacar.mapper.MapperService;
import com.app.Rentacar.model.Rate;
import com.app.Rentacar.service.rate.RateService;
import com.app.Rentacar.tools.tools;


@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class RateControllerTest extends AbstractTest{
	@Mock private RateService rateService;
	@Mock private MapperService<RateDTO, Rate> mapper;
	
	@InjectMocks private RateController rateController;
	
	private MockMvc mockMvc;
	private RateDTO mockRateDTO;
	private Rate mockRate;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(rateController).build();
	}

	@Before
	public void init() throws DateFormatNoValidException {
		mockRateDTO = new RateDTO();
		mockRateDTO.setId(ID);
		mockRateDTO.setStartDate(START_DATE);;
		mockRateDTO.setEndDate(END_DATE);
		mockRateDTO.setPrice(PRICE_RATE);

		mockRate= new Rate();
		mockRate.setStartRate(tools.convertStringToLocalDate(START_DATE));;
		mockRate.setEndRate(tools.convertStringToLocalDate(END_DATE));
		mockRate.setPriceRate(PRICE_RATE);
		mockRate.setDateCreatedRate(new Date());
	}
	
	@Test
	public void createRateController() throws Exception {
		// given
		String uri = "/rate";
		// when
		when(mapper.mapToDto(rateService.createRateService(mockRateDTO))).thenReturn(mockRateDTO);
		String inputJson = super.mapToJson(mockRateDTO);
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		// that
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, inputJson);
	}
}