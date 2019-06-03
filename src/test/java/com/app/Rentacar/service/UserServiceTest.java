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

import com.app.Rentacar.dao.UserRepository;
import com.app.Rentacar.dto.UserDTO;
import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.exceptions.DateFormatNoValidException;
import com.app.Rentacar.exceptions.UserFoundExistException;
import com.app.Rentacar.exceptions.UserNotFoundException;
import com.app.Rentacar.mapper.MapperService;
import com.app.Rentacar.model.User;
import com.app.Rentacar.service.user.UserServiceImpl;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock private UserRepository mockUserRepository;
	@Mock private MapperService<UserDTO, User> mockMapper;
	
	@Spy
	@InjectMocks private UserServiceImpl mockUserService = new UserServiceImpl(); 

	private UserDTO mockUserDTO;
	private User mockUser;

	private static final Integer ID=1;
	private static final String DNI="48903865T";
	private static final String NAME="LUIS GARCIA";
	
	@Before
	public void init() {
			mockUserDTO = new UserDTO();
			mockUserDTO.setId(ID);
			mockUserDTO.setDni(DNI);
			mockUserDTO.setName(NAME);

			mockUser = new User();
			mockUser.setId(ID);
			mockUser.setDni(DNI);
			mockUser.setName(NAME);
			mockUser.setDateCreatedUser(new Date());
			mockUser.setState(StateEnum.ACTIVE);		
	}
	
	@Test
	public void createUserTest() throws UserFoundExistException, DateFormatNoValidException {
		User userNew = new User();
		// when
		when(mockMapper.mapToEntity(mockUserDTO)).thenReturn(userNew);
		when(mockUserRepository.save(userNew)).thenReturn(userNew);
		userNew = mockUserService.createUserService(mockUserDTO);
		// then
		assertNotNull(userNew);
		Mockito.verify(mockUserService, Mockito.times(1)).createUserService(mockUserDTO);
	}
	
	@Test
	public void updateUserTest() throws UserNotFoundException {
		
		when(mockUserRepository.findByIdAndState(ID, StateEnum.ACTIVE)).thenReturn(mockUser);
		when(mockUserRepository.save(mockUser)).thenReturn(mockUser);
		
		mockUser = mockUserService.updateUserService(ID, mockUserDTO);
		
		assertNotNull(mockUser);
		Mockito.verify(mockUserRepository, Mockito.times(1)).save(mockUser);
	}
	@Test
	public void getUserFindIdAndStateTest() throws UserNotFoundException {

		when(mockUserRepository.findByIdAndState(ID, StateEnum.ACTIVE)).thenReturn(mockUser);
		when(mockUserService.getUserService(ID, StateEnum.ACTIVE)).thenReturn(mockUser);

		assertNotNull(mockUserDTO);
		assertEquals(mockUserService.getUserService(ID, StateEnum.ACTIVE), mockUser);
	}
	
	@Test
	public void deleteUserTest() throws UserNotFoundException {

		when(mockUserRepository.findByIdAndState(ID, StateEnum.ACTIVE)).thenReturn(mockUser);
		mockUser.setState(StateEnum.DISABLE);
		
		mockUserService.deleteUserService(ID);
		
		Mockito.verify(mockUserRepository, Mockito.times(1)).save(mockUser);
	}
}