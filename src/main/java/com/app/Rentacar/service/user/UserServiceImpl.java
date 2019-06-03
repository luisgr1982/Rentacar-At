package com.app.Rentacar.service.user;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.Rentacar.dao.UserRepository;
import com.app.Rentacar.dto.UserDTO;
import com.app.Rentacar.enums.StateEnum;
import com.app.Rentacar.exceptions.DateFormatNoValidException;
import com.app.Rentacar.exceptions.UserFoundExistException;
import com.app.Rentacar.exceptions.UserNotFoundException;
import com.app.Rentacar.mapper.MapperService;
import com.app.Rentacar.model.User;
import com.app.Rentacar.service.rent.RentService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired private MapperService<UserDTO, User> mapper;
	@Autowired private UserRepository userRepository;
	@Autowired private RentService rentService;
		
	@Override
	public User createUserService(UserDTO userDTO) throws UserFoundExistException, DateFormatNoValidException  {
		final Optional<User> user = Optional.ofNullable(mapper.mapToEntity(userDTO));
		if (Optional.ofNullable((user.get().getId())).isPresent() && 
				Optional.ofNullable(userRepository.findById(userDTO.getId())).isPresent())
				throw new UserFoundExistException();
		user.get().setState(StateEnum.ACTIVE);
		user.get().setDateCreatedUser(new Date());		
		return userRepository.save(user.get());
	}

	@Override
	public User getUserService(Integer idUser, StateEnum stateUser) throws UserNotFoundException {
		return Optional.ofNullable(userRepository.findByIdAndState(idUser, stateUser))
				.orElseThrow(UserNotFoundException::new);
	}

	@Override
	public Page<User> getListUserService(StateEnum state, Pageable pageable) {
		return Optional.ofNullable(userRepository.findByState(state, pageable)).get();
	}

	@Override
	public void deleteUserService(Integer idUser) throws UserNotFoundException {
		final User user = this.getUserService(idUser, StateEnum.ACTIVE);
		user.setState(StateEnum.DISABLE);
		userRepository.save(user);
	}

	@Override
	public User updateUserService(Integer idUser, UserDTO userUpdate) throws UserNotFoundException {
		final User user = this.getUserService(idUser, StateEnum.ACTIVE);
		user.setDni(userUpdate.getDni());
		user.setName(userUpdate.getName());
		return userRepository.save(user);
	}
	
	@Override
	public List<?> getListRentUserService(Integer idUser, Pageable pageable) throws UserNotFoundException {
		final User user = this.getUserService(idUser, StateEnum.ACTIVE);
		return rentService.getListRent(user.getRents());
	}
}