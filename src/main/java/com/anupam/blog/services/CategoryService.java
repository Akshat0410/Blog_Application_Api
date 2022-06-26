package com.anupam.blog.services;

import java.util.List;

import com.anupam.blog.payloads.CategoryDto;

public interface CategoryService {
	
	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	
	//update  Taking as parameter the new category item and the id of the cold category to be updated
	CategoryDto updateCategory(CategoryDto categoryDto,Integer oldCategoryId);
	
	//delete  Taking as parameter the id of category to be deleted
	void deleteCategory(Integer categoryId);
	
	
	//get
	CategoryDto getCategory(Integer categoryId);
	
	
	//getAll
	List<CategoryDto> getCategories();

}
