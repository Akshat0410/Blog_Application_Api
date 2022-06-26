package com.anupam.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotBlank
	@Size(min = 3,message = "Title size should be greater than 3")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 10,message = "Description should be descriptive enough")
	private String categoryDescription;

}
