package com.application.blogging.service;

import java.util.List;

import com.application.blogging.exceptionhandler.ResourceNotFoundException;
import com.application.blogging.model.dto.CategoryDto;
import com.application.blogging.model.response.CategoryResponse;

public interface CategoryService {
	
	CategoryResponse createCategory(CategoryDto dto);

	CategoryResponse updateCategory(CategoryDto dto, Long id) throws ResourceNotFoundException;

	List<CategoryResponse> getAllCategories();

	CategoryResponse getByCategory(Long id) throws ResourceNotFoundException;

	void DeleteCategoryById(Long id) throws ResourceNotFoundException;
}
