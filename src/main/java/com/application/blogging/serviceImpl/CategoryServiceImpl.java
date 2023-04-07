package com.application.blogging.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.blogging.exceptionhandler.ResourceNotFoundException;
import com.application.blogging.model.Category;
import com.application.blogging.model.dto.CategoryDto;
import com.application.blogging.model.response.CategoryResponse;
import com.application.blogging.repository.CategoryRepository;
import com.application.blogging.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryResponse createCategory(CategoryDto dto) {
		Category category = mapper.map(dto, Category.class);
		categoryRepository.save(category);
		return this.mapper.map(category, CategoryResponse.class);
	}

	@Override
	public CategoryResponse updateCategory(CategoryDto dto, Long id) throws ResourceNotFoundException {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("given id not present in the Db", 404));
		mapper.map(dto, category);
		categoryRepository.save(category);
		return this.mapper.map(category, CategoryResponse.class);
	}

	@Override
	public List<CategoryResponse> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return categories.stream().map(user -> this.mapper.map(user, CategoryResponse.class))
				.collect(Collectors.toList());
	}

	@Override
	public CategoryResponse getByCategory(Long id) throws ResourceNotFoundException {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("category is not found", 404));
		return mapper.map(category, CategoryResponse.class);
	}

	@Override
	public void DeleteCategoryById(Long id) throws ResourceNotFoundException {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("category id not found in Db", 404));
		categoryRepository.delete(category);
	}

}
