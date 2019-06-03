package com.app.Rentacar.exceptions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import com.app.Rentacar.dao.UserRepository;
import com.app.Rentacar.dto.UserDTO;
import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.mapper.MapperService;
import com.app.Rentacar.model.User;
import com.app.Rentacar.service.user.UserServiceImpl;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class UserNotFoundExceptionTest {
	
	@Mock private UserRepository mockUserRepository;
	@Mock private MapperService<UserDTO, User> mockMapper;
	
	@Spy
	@InjectMocks private UserServiceImpl mockUserService = new UserServiceImpl(); 
	
	@Test(expected = UserNotFoundException.class)
	public void ifUserNoExistThrowUserNotFoundExceptionTest() throws UserNotFoundException {
		Integer idNoExist = 2;
	    Mockito
	       .when(mockUserService.getUserService(idNoExist, StateEnum.ACTIVE))
	       .thenThrow(UserNotFoundException.class);	    	    
	}
}
