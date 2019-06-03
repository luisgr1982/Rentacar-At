package com.app.Rentacar.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import com.app.Rentacar.dao.CarRepository;
import com.app.Rentacar.dto.CarDTO;
import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.exceptions.CarNotFoundException;
import com.app.Rentacar.mapper.MapperService;
import com.app.Rentacar.model.Car;
import com.app.Rentacar.service.car.CarServiceImpl;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class CarServiceTest {

	@Mock private CarRepository mockCarRepository;
	@Mock private MapperService<CarDTO, Car> mockMapper;
	
	@Spy
	@InjectMocks private CarServiceImpl mockCarService = new CarServiceImpl(); 

	private CarDTO mockCarDTO;
	private Car mockCar;

	private static final Integer ID=1;
	private static final String CAR_PLATE="1111AAA";
	private static final String REGISTRATION_YEAR="2019";

	@Before
	public void init() {
			mockCarDTO = new CarDTO();
			mockCarDTO.setId(ID);
			mockCarDTO.setCarPlate(CAR_PLATE);
			mockCarDTO.setRegistrationYear(REGISTRATION_YEAR);

			mockCar = new Car();
			mockCar.setId(ID);
			mockCar.setCarPlate(CAR_PLATE);;
			mockCar.setRegistrationYear(REGISTRATION_YEAR);
			mockCar.setDateCreatedCar(new Date());
			mockCar.setState(StateEnum.ACTIVE);		
	}
		
	@Test
	public void updateCarTest() throws CarNotFoundException {
		
		when(mockCarRepository.findByIdAndState(ID, StateEnum.ACTIVE)).thenReturn(mockCar);
		when(mockCarRepository.save(mockCar)).thenReturn(mockCar);
		
		mockCar = mockCarService.updateCarService(ID, mockCarDTO);
		
		assertNotNull(mockCar);
		Mockito.verify(mockCarRepository, Mockito.times(1)).save(mockCar);
	}

	@Test
	public void getCarFindIdAndStateTest() throws CarNotFoundException {

		when(mockCarRepository.findByIdAndState(ID, StateEnum.ACTIVE)).thenReturn(mockCar);
		when(mockCarService.getCarService(ID, StateEnum.ACTIVE)).thenReturn(mockCar);

		assertNotNull(mockCarDTO);
		assertEquals(mockCarService.getCarService(ID, StateEnum.ACTIVE), mockCar);
	}
	
	@Test
	public void deleteCarTest() throws CarNotFoundException {

		when(mockCarRepository.findByIdAndState(ID, StateEnum.ACTIVE)).thenReturn(mockCar);
		mockCar.setState(StateEnum.DISABLE);
		
		mockCarService.deleteCarService(ID);
		
		Mockito.verify(mockCarRepository, Mockito.times(1)).save(mockCar);
	}
}
