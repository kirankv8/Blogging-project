package com.application.blogging.model.response;

import java.util.Date;

import com.application.blogging.model.Category;
import com.application.blogging.model.User;
import com.application.blogging.model.dto.CategoryDto;
import com.application.blogging.model.dto.UserDto;

import lombok.Data;
@Data
public class PostResponse {

	private Long id;

	private String title;

	private String content;

	private String imageName;

	private Date addedDate;
	
	private UserDto user;

	private CategoryDto category;

	

}
