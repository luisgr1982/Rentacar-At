package com.app.Rentacar.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

import com.app.Rentacar.dto.UserDTO;
import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.exceptions.DateFormatNoValidException;
import com.app.Rentacar.exceptions.UserFoundExistException;
import com.app.Rentacar.exceptions.UserNotFoundException;
import com.app.Rentacar.mapper.MapperService;
import com.app.Rentacar.model.User;
import com.app.Rentacar.service.user.UserService;
import com.app.Rentacar.tools.Constants;

@RestController
@RequestMapping(Constants.URI_USER_CONTROLLER)

public class UserController {
	@Autowired private UserService userService;
	@Autowired private MapperService<UserDTO, User> mapper;
	
	@GetMapping
	public ResponseEntity<Page<UserDTO>> getAllUser(@PageableDefault(page = 0, value = 10) Pageable pageable) {
		return new ResponseEntity<>(mapper.mapPageToDto(userService.getListUserService(StateEnum.ACTIVE, pageable)), HttpStatus.OK);		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("id") Integer idUser) throws UserNotFoundException{
		return new ResponseEntity<UserDTO>(mapper.mapToDto(userService.getUserService(idUser,StateEnum.ACTIVE)), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO user) throws UserFoundExistException, DateFormatNoValidException {
		return new ResponseEntity<UserDTO>(mapper.mapToDto(userService.createUserService(user)), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Integer idUser) throws UserNotFoundException{
		userService.deleteUserService(idUser);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> putUser(@PathVariable("id") Integer idUser, @Valid @RequestBody UserDTO userDTO)  throws UserNotFoundException {		
		return new ResponseEntity<UserDTO>(mapper.mapToDto(userService.updateUserService(idUser, userDTO)), HttpStatus.OK);
	}
	@GetMapping("/{id}" + Constants.URI_USER_RENT_CONTROLLER)
	public ResponseEntity<?> getListRentUser(@PageableDefault(page = 0, value = 10) Pageable pageable, @PathVariable("id") Integer idUser) throws UserNotFoundException{
		List<?> listUserRent = userService.getListRentUserService(idUser, pageable);
		return new ResponseEntity<>(new PageImpl<>(listUserRent, pageable, listUserRent.size()), HttpStatus.OK);
	}
}