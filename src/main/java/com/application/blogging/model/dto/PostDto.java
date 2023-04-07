package com.application.blogging.model.dto;

import java.util.Date;

import com.application.blogging.model.Category;
import com.application.blogging.model.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class PostDto {

	private String title;

	private String content;

	private String imageName;

	private Date addedDate;

	private CategoryDto category;
		
	private UserDto user;

}
