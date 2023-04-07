package com.application.blogging.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.blogging.exceptionhandler.ResourceNotFoundException;
import com.application.blogging.model.User;
import com.application.blogging.model.dto.UserDto;
import com.application.blogging.model.response.UserResponse;
import com.application.blogging.repository.UserRepository;
import com.application.blogging.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public UserResponse createUser(UserDto userDto) {
		User user = mapper.map(userDto, User.class);
		userRepo.save(user);
		return this.mapper.map(user, UserResponse.class);
	}

	@Override
	public UserResponse updateUser(UserDto userDto, Long id) throws ResourceNotFoundException {
		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("given id not present in the Db", 404));
		mapper.map(userDto, user);
		userRepo.save(user);
		return this.mapper.map(user, UserResponse.class);
	}

	@Override
	public List<UserResponse> getAllUsers() {
		List<User> users = userRepo.findAll();
//		List<UserResponse> ls = new ArrayList<>();
//		for (User user : users) {
//			UserResponse userdto = mapper.map(user, UserResponse.class);
//			ls.add(userdto);
//		}
//		return ls;
		return users.stream().map(user -> this.mapper.map(user, UserResponse.class)).collect(Collectors.toList());
	}

	@Override
	public UserResponse getByUserId(Long id)throws ResourceNotFoundException {
		User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("userid not found", 404));
		return mapper.map(user, UserResponse.class);
	}

	@Override
	public void DeleteUserById(Long id) throws ResourceNotFoundException{
		User user = userRepo.findById(id).orElseThrow( () -> new ResourceNotFoundException("user id not found in Db", 404));
		userRepo.delete(user);
	}
}
