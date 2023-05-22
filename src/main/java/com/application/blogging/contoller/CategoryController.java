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
import com.application.blogging.model.dto.CategoryDto;
import com.application.blogging.model.response.CategoryResponse;
import com.application.blogging.service.CategoryService;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/category/save")
	public CategoryResponse createCategory(@RequestBody CategoryDto dto) {
		return categoryService.createCategory(dto);
	}
	@PutMapping("/category/{id}")
	public CategoryResponse updatCategory(@RequestBody CategoryDto dto, @PathVariable Long id)
			throws ResourceNotFoundException {
		return categoryService.updateCategory(dto, id);
	}
	@GetMapping("category/getall")
	public List<CategoryResponse>getAlCategory(){
		return categoryService.getAllCategories();
	}
	@GetMapping("category/{id}")
	public CategoryResponse getByCategory(@PathVariable Long id)throws ResourceNotFoundException{
		return categoryService.getByCategory(id);
	}
	@DeleteMapping("category/delete/{id}")
	public String deleteCategory(@PathVariable Long id) throws ResourceNotFoundException {
		categoryService.DeleteCategoryById(id);
		 return "category_deletted successfully";
	}


}
