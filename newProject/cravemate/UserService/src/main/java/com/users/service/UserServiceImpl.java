package com.users.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.users.custom_exceptions.AuthenticationException;
import com.users.custom_exceptions.ResourceNotFoundException;
import com.users.custom_exceptions.UserAlreadyExistsException;
import com.users.dto.AuthRequest;
import com.users.dto.SignupRequest;
import com.users.dto.UserRespDTO;
import com.users.entities.User;
import com.users.entities.UserRole;
import com.users.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	private ModelMapper modelMapper;

	@Override
	public UserRespDTO addNewCustomer(SignupRequest request) {
		if (userRepository.existsByEmail(request.getEmail()))
			throw new UserAlreadyExistsException("User with same email already exists !!!");
		User user = modelMapper.map(request, User.class);
		user.setRole(UserRole.CUSTOMER);
		return modelMapper.map(userRepository.save(user), UserRespDTO.class);
	}

	@Override
	public UserRespDTO getUserDetails(Long userId) {
		User user = userRepository.findById(userId)
		.orElseThrow(() -> 
		new ResourceNotFoundException("User with ID " + userId + " not found !!!!"));
		return modelMapper.map(user, UserRespDTO.class);				
	}
	
	@Override
	public UserRespDTO signIn(AuthRequest dto) {
		// 1. invoke dao's method
		User userEntity = userRepository.findByEmailAndPassword
				(dto.getEmail(), dto.getPassword())
				.orElseThrow(() -> new AuthenticationException("Invalid Email or password !!!!!"));
		// user entity : persistent -> dto
		return modelMapper.map(userEntity, UserRespDTO.class);
	}


}
