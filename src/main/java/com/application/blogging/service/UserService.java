package com.application.blogging.service;

import java.util.List;

import com.application.blogging.exceptionhandler.ResourceNotFoundException;
import com.application.blogging.model.dto.UserDto;
import com.application.blogging.model.response.UserResponse;

public interface UserService {

	UserResponse createUser(UserDto userDto);

	UserResponse updateUser(UserDto userDto, Long id) throws ResourceNotFoundException;

	List<UserResponse> getAllUsers();

	UserResponse getByUserId(Long id) throws ResourceNotFoundException;

	void DeleteUserById(Long id) throws ResourceNotFoundException;

}
