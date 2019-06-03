package com.app.Rentacar.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.app.Rentacar.dto.UserDTO;
import com.app.Rentacar.model.User;

@Component
public class MapperServiceUserImpl implements MapperService<UserDTO, User> {	
	
	@Override
	public UserDTO mapToDto(User user) {
		final UserDTO userDTO = new UserDTO();
		if (Optional.ofNullable(user).isPresent()) {
			userDTO.setId(user.getId());
			userDTO.setDni(user.getDni());
			userDTO.setName(user.getName());
		}
		return userDTO;
	}

	@Override
	public User mapToEntity(UserDTO dto) {
		final User user = new User();
		if (Optional.ofNullable(dto).isPresent()) {
			user.setId(dto.getId());
			user.setDni(dto.getDni());
			user.setName(dto.getName());
		}
		return user;
	}

	@Override
	public Page<UserDTO> mapPageToDto(Page<User> pageEntity) {
		return pageEntity.map(b-> mapToDto(b));
	}

	@Override
	public List<UserDTO> mapListToDto(List<User> listUser) {
		final List<UserDTO> listUserDTO = new ArrayList<>();
		listUser.forEach((b)-> {
			final UserDTO userDTO = mapToDto(b);			
			listUserDTO.add(userDTO);		
		});
		return listUserDTO;
	}
}