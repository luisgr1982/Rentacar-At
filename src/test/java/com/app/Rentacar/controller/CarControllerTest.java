package com.app.Rentacar.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.app.Rentacar.dto.CarDTO;
import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.mapper.MapperService;
import com.app.Rentacar.model.Car;
import com.app.Rentacar.service.car.CarService;

public class CarControllerTest extends AbstractTest{
	@Mock private CarService carService;
	@Mock private MapperService<CarDTO, Car> mapper;
	
	@InjectMocks private CarController carController;

	private MockMvc mockMvc;
	private CarDTO mockCarDTO;
	private Car mockCar;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
	}

	@Before
	public void init() {
		mockCarDTO = new CarDTO();
		mockCarDTO.setId(ID);
		mockCarDTO.setCarPlate(CAR_PLATE);
		mockCarDTO.setRegistrationYear(REGISTRATION_YEAR);

		mockCar = new Car();
		mockCar.setId(ID);
		mockCar.setCarPlate(CAR_PLATE);
		mockCar.setRegistrationYear(REGISTRATION_YEAR);
		mockCar.setDateCreatedCar(new Date());
		mockCar.setState(StateEnum.ACTIVE);
	}
	@Test
	public void getOneCarController() throws Exception {
		  String uri = "/car/1";
		  String inputJson = super.mapToJson(mockCarDTO);
		  
		  when(mapper.mapToDto(carService.getCarService(ID,StateEnum.ACTIVE))).thenReturn(mockCarDTO);
	      MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	      String content = mvcResult.getResponse().getContentAsString();

	      assertEquals(content, inputJson);  
	      assertEquals(200, status);
	}

	@Test
	public void createCarController() throws Exception {
		// given
		String uri = "/car";
		// when
		when(mapper.mapToDto(carService.createCarService(mockCarDTO))).thenReturn(mockCarDTO);
		String inputJson = super.mapToJson(mockCarDTO);
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		// that
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, inputJson);
	}

	@Test
	public void updateCarController() throws Exception {
		//given
		String uri = "/car/1";
		//when
		when(mapper.mapToDto(carService.updateCarService(ID, mockCarDTO))).thenReturn(mockCarDTO);
		String inputJson = super.mapToJson(mockCarDTO);
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		//that
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, inputJson);
	}

	@Test
	public void deleteCarController() throws Exception {
		//given
		String uri = "/car/1";
		//when
		carService.deleteCarService(ID);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		//that
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
}