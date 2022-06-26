package com.anupam.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	String resourceName;
	String fieldName;
	long fieldValue;
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(resourceName +" with field name"+fieldName+" not found in the databse for the field value " + fieldValue);
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
}
