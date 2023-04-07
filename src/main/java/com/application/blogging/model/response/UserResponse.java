package com.application.blogging.model.response;

import lombok.Data;

@Data
public class UserResponse {
	
	private Long id;
	
	private String name;

	private String about;

	private String email;
}
