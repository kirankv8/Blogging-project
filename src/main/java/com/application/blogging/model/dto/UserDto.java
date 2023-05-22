package com.application.blogging.model.dto;

import lombok.Data;

@Data
public class UserDto {

	private String name;

	private String about;

	private String email;

	private String password;
	
	private String roles;

}
