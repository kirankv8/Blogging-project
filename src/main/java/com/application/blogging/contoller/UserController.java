package com.application.blogging.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.blogging.exceptionhandler.ResourceNotFoundException;
import com.application.blogging.model.dto.UserDto;
import com.application.blogging.model.response.UserResponse;
import com.application.blogging.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public UserResponse createUserDto(@RequestBody UserDto userDto) {
		return userService.createUser(userDto);
	}

	@PutMapping("/update/{id}")
	public UserResponse updateDto(@RequestBody UserDto userDto, @PathVariable Long id)
			throws ResourceNotFoundException {
		return userService.updateUser(userDto, id);
	}
	@GetMapping("users/getall")
	public List<UserResponse>getAllusers(){
		return userService.getAllUsers();
	}
	@GetMapping("user/{id}")
	public UserResponse getByUser(@PathVariable Long id)throws ResourceNotFoundException{
		return userService.getByUserId(id);
	}
	@DeleteMapping("user/delete/{id}")
	public String userDelete(@PathVariable Long id) throws ResourceNotFoundException {
		 userService.DeleteUserById(id);
		 return "user_deletted successfully";
	}

}
