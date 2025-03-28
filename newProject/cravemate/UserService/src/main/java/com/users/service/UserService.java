package com.users.service;

import com.users.dto.AuthRequest;
import com.users.dto.SignupRequest;
import com.users.dto.UserRespDTO;

public interface UserService {
	  UserRespDTO addNewCustomer(SignupRequest request);
	  UserRespDTO getUserDetails(Long userId);
	  UserRespDTO signIn(AuthRequest dto);  		 
}
