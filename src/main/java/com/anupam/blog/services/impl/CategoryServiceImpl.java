package com.anupam.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anupam.blog.entities.Category;
import com.anupam.blog.exceptions.ResourceNotFoundException;
import com.anupam.blog.payloads.CategoryDto;
import com.anupam.blog.repositories.CategoryRepo;
import com.anupam.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category toBeSaved=this.modelMapper.map(categoryDto,Category.class);
		Category savedCategory=this.categoryRepo.save(toBeSaved);
		
		return this.modelMapper.map(savedCategory,CategoryDto.class);
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer oldCategoryId) {
		
		Category existing = this.categoryRepo.findById(oldCategoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId",oldCategoryId));
		
		existing.setCategoryTitle(categoryDto.getCategoryTitle());
		existing.setCategoryDescription(categoryDto.getCategoryDescription());
		
		//saving the data
		Category updatedCategory = this.categoryRepo.save(existing);
		
		
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
		
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category existing = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId",categoryId));
		this.categoryRepo.delete(existing);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId",categoryId));
		return this.modelMapper.map(category,CategoryDto.class);
		
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories=this.categoryRepo.findAll();
		
				
		List<CategoryDto> categoriesDto = categories.stream().map((category)-> this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());	
		
		return categoriesDto;
	}

}
