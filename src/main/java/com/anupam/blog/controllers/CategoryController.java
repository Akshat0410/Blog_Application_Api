package com.anupam.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anupam.blog.payloads.ApiResponse;
import com.anupam.blog.payloads.CategoryDto;
import com.anupam.blog.services.CategoryService;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto created=this.categoryService.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(created,HttpStatus.CREATED);
		
	}
	
	//update
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
		CategoryDto updated=this.categoryService.updateCategory(categoryDto,categoryId);
		
		return new ResponseEntity<CategoryDto>(updated,HttpStatus.OK);
		
	}
	
	//delete
	
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<ApiResponse> deletedCategory(@PathVariable Integer categoryId){
		
		this.categoryService.deleteCategory(categoryId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted", true),HttpStatus.OK);
		
	}
	
	//get
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer categoryId){
		
		return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
		
	}
	
	
	//getAll
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllUser(){
		
		return ResponseEntity.ok(this.categoryService.getCategories());
		
	}
	
	
	

}
