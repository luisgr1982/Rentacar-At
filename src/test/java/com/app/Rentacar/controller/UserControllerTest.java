package com.app.Rentacar.controller;

import static org.junit.Assert.assertEquals;

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

import com.app.Rentacar.dto.UserDTO;
import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.mapper.MapperService;
import com.app.Rentacar.model.User;
import com.app.Rentacar.service.user.UserService;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class UserControllerTest extends AbstractTest {
	
	@Mock private UserService userService;
	@Mock private MapperService<UserDTO, User> mapper;
	
	@InjectMocks private UserController userController;

	private UserDTO mockUserDTO;
	private User mockUser;	
	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

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
	public void getOneUserController() throws Exception {
		  String uri = "/user/1";
		  String inputJson = super.mapToJson(mockUserDTO);
		  
		  when(mapper.mapToDto(userService.getUserService(ID,StateEnum.ACTIVE))).thenReturn(mockUserDTO);
	      MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	      String content = mvcResult.getResponse().getContentAsString();

	      assertEquals(content, inputJson);  
	      assertEquals(200, status);
	}

	@Test
	public void createUserController() throws Exception {
		// given
		String uri = "/user";
		// when
		when(mapper.mapToDto(userService.createUserService(mockUserDTO))).thenReturn(mockUserDTO);
		String inputJson = super.mapToJson(mockUserDTO);
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
	public void updateUserController() throws Exception {
		//given
		String uri = "/user/1";
		//when
		when(mapper.mapToDto(userService.updateUserService(ID, mockUserDTO))).thenReturn(mockUserDTO);
		String inputJson = super.mapToJson(mockUserDTO);
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
	public void deleteUserController() throws Exception {
		//given
		String uri = "/user/1";
		//when
		userService.deleteUserService(ID);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		//that
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
}